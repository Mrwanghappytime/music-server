package com.mrwang.happytime.musicserver.service.imp;

import com.mrwang.happytime.musicserver.dao.AdminDao;
import com.mrwang.happytime.musicserver.po.Admin;
import com.mrwang.happytime.musicserver.shiro.token.LoginTypeUsernamePasswordToken;
import com.mrwang.happytime.musicserver.vo.Result;
import com.mrwang.happytime.musicserver.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImp implements AdminService {
    @Override
    public Result findAdmin(Admin admin) {
        Subject subject = SecurityUtils.getSubject();
        //Session session = subject.getSession();
        try{
            if(!subject.isAuthenticated()){
                UsernamePasswordToken usernamePasswordToken = new LoginTypeUsernamePasswordToken(admin.getName(),admin.getPassword(),"admin");
                subject.login(usernamePasswordToken);
                return Result.getResult(1,"登录成功");
            }
            return Result.getResult(1,"登录成功");
        }catch (Exception e){
            return Result.getResult(0,"用户名或者密码错误");
        }
    }
}
