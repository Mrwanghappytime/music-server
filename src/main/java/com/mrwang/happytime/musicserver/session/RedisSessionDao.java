package com.mrwang.happytime.musicserver.session;

import com.mrwang.happytime.musicserver.redis.RedisManager;
import lombok.SneakyThrows;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("sessionDAO")
public class RedisSessionDao extends AbstractSessionDAO {
    @Autowired
    private RedisManager redisManager;

    private static String sessionPrefix = "shiro-session:";

    /**
     * 创建session，并且存储到redis
     * @param session
     * @return
     */

    @SneakyThrows
    @Override
    protected Serializable doCreate(Session session) {
        Serializable serializable = this.generateSessionId(session);
        this.assignSessionId(session,serializable);
        this.saveSession(session);
        return serializable;
    }

    @SneakyThrows
    @Override
    protected Session doReadSession(Serializable serializable) {
        if(serializable == null){
            return null;
        }
        Object stringSerializable = redisManager.get(sessionPrefix + serializable);
        return (Session) stringSerializable;

    }

    @SneakyThrows
    @Override
    public void update(Session session) throws UnknownSessionException {
        this.saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if(session == null || session.getId() == null){
            return;
        }
        redisManager.del(sessionPrefix + session.getId());
    }

    @SneakyThrows
    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<>();
        Set<String> keys = redisManager.keys(sessionPrefix + "*");
        for(String s:keys){
            sessions.add((Session) redisManager.get(s));
        }
        return sessions;
    }
    private void saveSession(Session session) throws IOException {
        if(session ==  null || session.getId() == null){
            return;
        }
        redisManager.set(sessionPrefix + session.getId(),session, (long) (60*30*1000));
    }
}
