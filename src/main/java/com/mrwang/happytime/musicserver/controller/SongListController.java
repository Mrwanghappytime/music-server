package com.mrwang.happytime.musicserver.controller;

import com.mrwang.happytime.musicserver.annotation.ParameterCheck;
import com.mrwang.happytime.musicserver.po.Song;
import com.mrwang.happytime.musicserver.po.SongList;
import com.mrwang.happytime.musicserver.service.SongListService;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/songList")
public class SongListController {
    @Autowired
    private SongListService songListService;

    @PostMapping("/add")
    //title&pic&introduction&style
    @ParameterCheck("title")
    @ParameterCheck("introduction")
    @ParameterCheck("style")
    @ParameterCheck("pic")
    public Result add(SongList songList){
        return Result.getResult(0,"功能还未实现");
    }
    @GetMapping("")
    public List<SongList> root(){
        return songListService.getAll();
    }
    @GetMapping("/detail")
    public List<SongList> detail(@RequestParam("songListId") int  id){
        SongList songList = new SongList();
        songList.setId(id);
        return songListService.getSongListsById(songList);
    }
    @ParameterCheck("title")
    @GetMapping("/title/detail")
    public List<SongList> titleDetail(SongList songList){
        return songListService.getSongListsByTitle(songList);
    }
    @GetMapping("/likeTitle/detail")
    @ParameterCheck("title")
    public List<SongList> likeTitleDetail(SongList songList){
        return songListService.getSongListsByTitleLike(songList);
    }
    @GetMapping("/style/detail")
    @ParameterCheck("style")
    public List<SongList> styleDetail(SongList songList){
        return songListService.getSongByStyle(songList);
    }
    @GetMapping("/delete")
    @ParameterCheck("id")
    public boolean delete(SongList songList){
        return songListService.deleteSongList(songList);
    }
    @PostMapping ("/update")
    public Result update(SongList songList){
        return songListService.updateSongList(songList);
    }
    @PostMapping("/img/update")
    //@ParameterCheck("id")
    public Result imgUpdate(SongList songList,@RequestParam("file") MultipartFile file){
        return songListService.updateSongListImg(songList,file);
    }


}
