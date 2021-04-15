package com.mrwang.happytime.musicserver;

import com.mrwang.happytime.musicserver.dao.AdminDao;
import com.mrwang.happytime.musicserver.dao.ConsumerDao;
import com.mrwang.happytime.musicserver.po.Admin;
import com.mrwang.happytime.musicserver.po.Consumer;
import com.mrwang.happytime.musicserver.redis.RedisManager;
import com.mrwang.happytime.musicserver.util.MD5Encode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

import javax.security.auth.login.Configuration;
import java.util.List;

@SpringBootApplication
@MapperScan("com.mrwang.happytime.musicserver.dao")
@ServletComponentScan(basePackages = "com.mrwang.happytime.musicserver.filter")
public class MusicServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MusicServerApplication.class, args);
        //修改admin和consumer的密码，只需在第一次启动时打开注释,但是需要先记住数据库中的密码
//        AdminDao adminDao = run.getBean("adminDao", AdminDao.class);
//        ConsumerDao consumerDao = run.getBean("consumerDao", ConsumerDao.class);
//        updateAdminPassword(adminDao);
//        updateConsumerPassword(consumerDao);
    }

    /**
     * 修改amdin密码，改为加密密码，先记录密码，然后在进行修改,只需第一次启动项目调用一次
     * @param adminDao
     */
    public static void updateAdminPassword(AdminDao adminDao){
        List<Admin> all = adminDao.findAll();
        for(Admin admin : all){
            admin.setPassword(MD5Encode.encode(admin.getName(),admin.getPassword()));
            adminDao.update(admin);
        }
    }

    /**
     * 修改consumer密码，改为加密密码，先记录密码，然后在进行修改，只需第一次启动项目调用一次
     * @param consumerDao
     */
    public static void updateConsumerPassword(ConsumerDao consumerDao){
        List<Consumer> consumers = consumerDao.selectAll();
        for(Consumer consumer : consumers){
            consumer.setPassword(MD5Encode.encode(consumer.getUsername(),consumer.getPassword()));
            consumerDao.updateConsumer(consumer);
        }
    }



}
