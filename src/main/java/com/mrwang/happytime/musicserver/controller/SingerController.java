package com.mrwang.happytime.musicserver.controller;

import com.mrwang.happytime.musicserver.annotation.ParameterCheck;
import com.mrwang.happytime.musicserver.po.Singer;
import com.mrwang.happytime.musicserver.service.SingerService;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    private SingerService singerService;
    //name&sex&pic&birth&location&introduction
    @PostMapping("/add")
    @ParameterCheck("name")
    @ParameterCheck("sex")
    @ParameterCheck("birth")
    @ParameterCheck("location")
    @ParameterCheck("introduction")
    public Result add(Singer singer){
        return singerService.insertSinger(singer);
    }
    @GetMapping("")
    public List<Singer> root(){
        return singerService.getAllSinger();
    }
    @GetMapping("/name/detail")
    @ParameterCheck("name")
    public List<Singer> nameDetail(Singer singer){
        return singerService.getSingerByName(singer);
    }
    @GetMapping("/sex/detail")
    @ParameterCheck("sex")
    public List<Singer> sexDetail(Singer singer){
        return singerService.getSingerBySex(singer);
    }
    @GetMapping("/delete")
    @ParameterCheck("id")
    public boolean delete(Singer singer){
        return singerService.delete(singer);
    }
    @PostMapping("/update")
    @ParameterCheck("id")
    @ParameterCheck(value = "name",notNull = false)
    @ParameterCheck(value = "sex",notNull = false)
    @ParameterCheck(value = "birth",notNull = false)
    @ParameterCheck(value = "location",notNull = false)
    @ParameterCheck(value = "introduction",notNull = false)
    public Result update(Singer singer){
        return singerService.updateSinger(singer);
    }
    @PostMapping("/avatar/update")
   // @ParameterCheck("id")
    public Result avatarUpdate(Singer singer, @RequestParam("file")MultipartFile file){
        JSONObject jsonObject = new JSONObject();
        return singerService.updateSingerAvatar(singer,file);

    }
}
