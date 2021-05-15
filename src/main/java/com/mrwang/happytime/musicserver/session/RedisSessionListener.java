package com.mrwang.happytime.musicserver.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RedisSessionListener implements SessionListener {



    @Override
    public void onStart(Session session) {
        System.out.println("绘画创建");
    }

    @Override
    public void onStop(Session session) {
        System.out.println("会话停止");
    }

    @Override
    public void onExpiration(Session session) {
        Serializable id = session.getId();

        System.out.println("会话过期");
    }
}
