package com.mrwang.happytime.musicserver.controller;

import com.mrwang.happytime.musicserver.annotation.ParameterCheck;
import com.mrwang.happytime.musicserver.annotation.ParameterChecks;
import com.mrwang.happytime.musicserver.po.Admin;
import com.mrwang.happytime.musicserver.service.AdminService;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;
    @ParameterCheck(value = "name",type=String.class)
    @ParameterCheck(value = "password",type=String.class)
    @PostMapping("/admin/login/status")
    public Result adminLoginStatus(Admin admin){
        return adminService.findAdmin(admin);
    }

}
