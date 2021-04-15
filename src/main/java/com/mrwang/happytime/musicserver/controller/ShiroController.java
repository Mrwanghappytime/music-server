package com.mrwang.happytime.musicserver.controller;

import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroController {

    @RequestMapping("/loginUrl")
    public Result loginUrl(){
        return Result.getResult(0,"请先登录");
    }
    @RequestMapping("/unauthorizedUrl")
    public Result unauthorizedUrl(){
        return Result.getResult(0,"没有权限");
    }
}
