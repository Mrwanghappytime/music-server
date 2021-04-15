package com.mrwang.happytime.musicserver.redis;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisManager {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    public void del(String s) {
        redisTemplate.delete(s);
    }

    public Set<String> keys(String s) {
        Set<String> keys = redisTemplate.keys(s);
        return keys;
    }

    public void set(String s, Session session, long l) {
        System.out.println("set:" + s);
        redisTemplate.opsForValue().set(s,session,l,TimeUnit.SECONDS);

    }

    public Object get(String s) {
        System.out.println("get:" + s);
        return redisTemplate.opsForValue().get(s);
    }
}
