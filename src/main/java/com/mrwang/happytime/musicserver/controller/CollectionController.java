package com.mrwang.happytime.musicserver.controller;

import com.mrwang.happytime.musicserver.annotation.ParameterCheck;
import com.mrwang.happytime.musicserver.po.Collect;
import com.mrwang.happytime.musicserver.service.CollectService;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectService collectService;
    @GetMapping("")
    public List<Collect> collects(){
        return collectService.getAllCollect();
    }
    @GetMapping("/detail")
    public List<Collect> detail(@RequestParam("userId") int userId){
        Collect collect = new Collect();
        collect.setUserId(userId);
        return collectService.detail(collect);
    }
    @ParameterCheck(value="useId")
    @ParameterCheck(value="songId")
    @GetMapping("/delete")
    public boolean delete(Collect collect){
        return collectService.delete(collect);
    }

    @PostMapping("/update")
    @ParameterCheck(value="id")
    @ParameterCheck(value="userId",notNull = false)
    @ParameterCheck(value = "type",notNull = false)
    @ParameterCheck(value = "songId",notNull = false)
    public Result update(Collect collect){
        return collectService.updateCollect(collect);
    }
    @PostMapping("/add")
    @ParameterCheck(value="type")
    @ParameterCheck(value = "songId")
    @ParameterCheck(value = "songListId")
    public Result add(Collect collect){
        return collectService.addCollect(collect);
    }
}
