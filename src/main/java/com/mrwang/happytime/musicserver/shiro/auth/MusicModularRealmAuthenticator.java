package com.mrwang.happytime.musicserver.shiro.auth;

import com.mrwang.happytime.musicserver.shiro.token.LoginTypeUsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

public class MusicModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        Realm realm = null;
        LoginTypeUsernamePasswordToken loginTypeUsernamePasswordToken = (LoginTypeUsernamePasswordToken) authenticationToken;
        Collection<Realm> realms = getRealms();
        ArrayList<Realm> realms1 = new ArrayList<>();
        for(Realm realm1 : realms){
            if(loginTypeUsernamePasswordToken.getLoginType().equals(realm1.getName())){
                realms1.add(realm1);
            }
        }
        if(realms1.size() == 1){
            realm = realms1.get(0);
            return doSingleRealmAuthentication(realm,authenticationToken);
        }else {
            return doMultiRealmAuthentication(realms1,authenticationToken);
        }
    }
}
