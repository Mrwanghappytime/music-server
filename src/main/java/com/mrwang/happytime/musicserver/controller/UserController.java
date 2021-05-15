package com.mrwang.happytime.musicserver.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mrwang.happytime.musicserver.annotation.ParameterCheck;
import com.mrwang.happytime.musicserver.po.Consumer;
import com.mrwang.happytime.musicserver.service.ConsumerService;
import com.mrwang.happytime.musicserver.vo.Result;
import com.mrwang.happytime.musicserver.vo.ResultLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ConsumerService consumerService;

    @GetMapping()
    public List<Consumer> root(){
        return consumerService.getAll();
    }
    @ParameterCheck("username")
    @ParameterCheck("password")
    @PostMapping("/login/status")
    public ResultLogin loginStatus(Consumer consumer){
        ResultLogin consumerByUserPassword = consumerService.findConsumerByUserPassword(consumer);
        return consumerByUserPassword;
    }

    @PostMapping("/add")
    public Result add(Consumer consumer){
        return consumerService.addConsumer(consumer);
    }
    @ParameterCheck("id")
    @GetMapping("/detail")
    public List<Consumer> detail(Consumer consumer){
        return consumerService.findConsumerById(consumer);
    }
    @ParameterCheck("id")
    @GetMapping("/delete")
    public boolean delete(Consumer consumer){
        return consumerService.deleteConsumerById(consumer);
    }
    @PostMapping("/update")
    public Result update(Consumer consumer){
        return consumerService.updateConsumer(consumer);
    }
    @PostMapping("/avatar/update")
    //@ParameterCheck("id")
    public Result  avatarUpdate(Consumer consumer,@RequestParam("file") MultipartFile file){
        return consumerService.avatarUpdate(file,consumer);

    }
}
