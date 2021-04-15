package com.mrwang.happytime.musicserver.controller;

import com.mrwang.happytime.musicserver.annotation.ParameterCheck;
import com.mrwang.happytime.musicserver.po.ListSong;
import com.mrwang.happytime.musicserver.service.ListSongService;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/listSong")
public class ListSongController {
    @Autowired
    private ListSongService listSongService;

    @GetMapping("")
    public List<ListSong> root(){
        return listSongService.getAllListSong();
    }
    @PostMapping("/add")
    @ParameterCheck("songId")
    @ParameterCheck("songListId")
    public Result add(ListSong listSong){
        return listSongService.insertListSong(listSong);
    }
    @GetMapping("/detail")
    @ParameterCheck("songListId")
    public List<ListSong> detail(ListSong listSong){
        return listSongService.getListSongBySongListId(listSong);
    }
    @GetMapping("/delete")
    @ParameterCheck("songId")
    public boolean delete(ListSong listSong){
        return listSongService.deleteListSong(listSong);
    }



}
