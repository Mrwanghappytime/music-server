package com.mrwang.happytime.musicserver.shiro.realm;

import com.mrwang.happytime.musicserver.dao.AdminDao;
import com.mrwang.happytime.musicserver.po.Admin;
import com.mrwang.happytime.musicserver.po.Consumer;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public class AdminRealm extends AuthorizingRealm {
    @Autowired
    private AdminDao adminDao;

    @Override
    public String getName() {
        return "admin";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String username = (String) principalCollection.getPrimaryPrincipal();
        Admin admin = new Admin();
        admin.setName(username);
        Admin byUsernameAndPassword = adminDao.findByUsernameAndPassword(admin);
        if(admin == null){
            return null;
        }
        //HttpSession session = request.getSession();
        //String aa = (String) session.getAttribute("username");
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        AuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        Set<String> perms =  new HashSet<>();
        roles.add("admin");
        perms.add("user");
        ((SimpleAuthorizationInfo) authorizationInfo).addRoles(roles);
        ((SimpleAuthorizationInfo) authorizationInfo).addStringPermissions(perms);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken){
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String username = usernamePasswordToken.getUsername();
        Admin admin = new Admin();
        admin.setName(username);
        Admin byUsernameAndPassword = adminDao.findByUsernameAndPassword(admin);
        return new SimpleAuthenticationInfo(byUsernameAndPassword.getName(),byUsernameAndPassword.getPassword(), ByteSource.Util.bytes(byUsernameAndPassword.getName()),getName());
    }
}
