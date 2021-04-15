package com.mrwang.happytime.musicserver.dao;

import com.mrwang.happytime.musicserver.po.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Mapper
public interface AdminDao {
    //@Select("select count(*) from admin where name=#{name} and password=#{password}")
    Admin findByUsernameAndPassword(Admin admin);

    int update(Admin byUsernameAndPassword);

    List<Admin> findAll();
}
