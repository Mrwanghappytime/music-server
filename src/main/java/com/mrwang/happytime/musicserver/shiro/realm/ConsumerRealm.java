package com.mrwang.happytime.musicserver.shiro.realm;

import com.mrwang.happytime.musicserver.dao.ConsumerDao;
import com.mrwang.happytime.musicserver.po.Admin;
import com.mrwang.happytime.musicserver.po.Consumer;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConsumerRealm extends AuthorizingRealm {
    @Autowired
    private ConsumerDao consumerDao;

    @Override
    public String getName() {
        return "consumer";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //从redis根据sessionId拿到Session对象,因为暂时没有集成redis，所以暂时先使用request去获得session
        Consumer consumer = new Consumer();
        consumer.setUsername((String) principalCollection.getPrimaryPrincipal());
        List<Consumer> consumers = consumerDao.selectConsumerByCommer(consumer);
        if(consumers.size() != 1){
            return null;
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //Set<String> roles = new HashSet<>();
        Set<String> perms = new HashSet<>();
        perms.add("user");
        simpleAuthorizationInfo.addStringPermissions(perms);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String username = usernamePasswordToken.getUsername();
        Consumer admin = new Consumer();
        admin.setUsername(username);
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        List<Consumer> consumers = consumerDao.selectConsumerByCommer(admin);
        if(consumers.size() != 1){
            throw new  UnknownAccountException("位置的用户" + admin.getUsername());
        }
        admin = consumers.get(0);
        session.setAttribute("user",admin);
        return new SimpleAuthenticationInfo(admin.getUsername(),admin.getPassword(), ByteSource.Util.bytes(admin.getUsername()),getName());
    }
}
