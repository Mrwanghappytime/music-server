package com.mrwang.happytime.musicserver.configuration;

import com.mrwang.happytime.musicserver.filter.ShiroCorsFilter;
import com.mrwang.happytime.musicserver.propeties.ShiroProperties;
import com.mrwang.happytime.musicserver.shiro.auth.MusicModularRealmAuthenticator;
import com.mrwang.happytime.musicserver.shiro.realm.AdminRealm;
import com.mrwang.happytime.musicserver.shiro.realm.ConsumerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(ShiroProperties.class)
public class ShiroConfiguration {
    @Autowired
    private ShiroProperties shiroProperties;


    @Bean("credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(ShiroProperties shiroProperties){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(shiroProperties.getHashAlgorithmName());
        hashedCredentialsMatcher.setHashIterations(shiroProperties.getHashIterations());
        return hashedCredentialsMatcher;
    }
    @Bean
    public AdminRealm adminRealm(HashedCredentialsMatcher credentialsMatcher){
        AdminRealm adminRealm = new AdminRealm();
        adminRealm.setCredentialsMatcher(credentialsMatcher);
        return adminRealm;
    }
    @Bean
    public ConsumerRealm consumerRealm(HashedCredentialsMatcher credentialsMatcher){
        ConsumerRealm adminRealm = new ConsumerRealm();
        adminRealm.setCredentialsMatcher(credentialsMatcher);
        return adminRealm;
    }
    @Bean
    public AuthenticationStrategy authenticationStrategy(ShiroProperties shiroProperties) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        AuthenticationStrategy authenticationStrategy = null;
        if(shiroProperties.getAuthenticationStrategyClassName() == null){
            authenticationStrategy = new AtLeastOneSuccessfulStrategy();
            return authenticationStrategy;
        }
        authenticationStrategy = (AuthenticationStrategy) Class.forName(shiroProperties.getAuthenticationStrategyClassName()).getConstructor().newInstance();
        return authenticationStrategy;
    }
    @Bean
    public ModularRealmAuthorizer modularRealmAuthorizer(AdminRealm adminRealm,ConsumerRealm consumerRealm){
        ModularRealmAuthorizer modularRealmAuthorizer = new ModularRealmAuthorizer();
        List<Realm> list = new ArrayList<>();
        list.add(adminRealm);
        list.add(consumerRealm);
        modularRealmAuthorizer.setRealms(list);
        return modularRealmAuthorizer;
    }
    @Bean
    public MusicModularRealmAuthenticator modularRealmAuthenticator(AdminRealm adminRealm, ConsumerRealm consumerRealm, AuthenticationStrategy authenticationStrategy){
        MusicModularRealmAuthenticator modularRealmAuthenticator = new MusicModularRealmAuthenticator();
        List<Realm> list = new ArrayList<>();
        list.add(adminRealm);
        list.add(consumerRealm);
        modularRealmAuthenticator.setRealms(list);
        modularRealmAuthenticator.setAuthenticationStrategy(authenticationStrategy);
        return modularRealmAuthenticator;
    }
    /**
     *注入WebSecurityManager
     */
//    @Bean
//    public SessionManager sessionManager(){
//        SessionManager sessionManager = new  DefaultWebSecurityManager();
//        sessionManager.set
//    }

    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO){
        //DefaultWebSecurityManager sessionManager = new DefaultWebSecurityManager();
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setGlobalSessionTimeout(30*60);
        sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(ModularRealmAuthenticator modularRealmAuthenticator, ModularRealmAuthorizer modularRealmAuthorizer,SessionManager sessionManager){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setAuthenticator(modularRealmAuthenticator);
        defaultWebSecurityManager.setAuthorizer(modularRealmAuthorizer);
        defaultWebSecurityManager.setSessionManager(sessionManager);
        //defaultWebSecurityManager.setSessionManager();
        return defaultWebSecurityManager;
    }



    /**
     * 配置shiroFilter bean
     *
     */

    @Bean
    public ShiroFilterFactoryBean delegatingFilterProxy(DefaultWebSecurityManager securityManager,ShiroProperties shiroProperties){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorizedUrl");
        shiroFilterFactoryBean.setLoginUrl("/loginUrl");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroProperties.getFilterMap());
        return shiroFilterFactoryBean;
    }

    /**
     * 注入DelegatingFilterProxy
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean1(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //ShiroFilter shiroFilter = new ShiroFilter();
        ShiroCorsFilter delegatingFilterProxy = new ShiroCorsFilter();
        filterRegistrationBean.setFilter(delegatingFilterProxy);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //ShiroFilter shiroFilter = new ShiroFilter();
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        filterRegistrationBean.setFilter(delegatingFilterProxy);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }




}
