package com.mrwang.happytime.musicserver.controller;

import com.mrwang.happytime.musicserver.annotation.ParameterCheck;
import com.mrwang.happytime.musicserver.po.Song;
import com.mrwang.happytime.musicserver.service.SongService;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;
    @GetMapping("")
    public List<Song> root(){
        return songService.getAllSong();
    }
    @GetMapping("/detail")
    @ParameterCheck("id")
    public List<Song> detail(Song song){
        return songService.getSongDetail(song);
    }
    @ParameterCheck("singerId")
    @GetMapping("/singer/detail")
    public List<Song> singerDetail(Song song){
        return songService.getSongBySingerId(song);
    }

    /**
     * 逻辑不对
     * @param song
     * @return
     */
    @GetMapping("/singerName/detail")
    @ParameterCheck("name")
    public List<Song> singerNameDetail(Song song){
        return songService.getSongBySingerName(song);
    }

    @GetMapping("/name/detail")
    @ParameterCheck("name")
    public List<Song> nameDetail(Song song){
        return songService.getSongBySongName(song);
    }

    @GetMapping("/delete")
    @ParameterCheck("id")
    public boolean delete(Song song){
        return songService.deleteSong(song);
    }

    @PostMapping("/update")
    public Result update(Song song){
        return songService.updateSong(song);
    }
    @PostMapping("/img/update")
    //@ParameterCheck("id")
    public Result imgUpdate(Song song ,@RequestParam("file")MultipartFile file){
        return songService.updateSongImg(song,file);
    }
    //@ParameterCheck("id")
    @PostMapping("/url/update")
    public Result urlUpdate(Song song,@RequestParam("file") MultipartFile file){
        return songService.updateSongUrl(song,file);
    }
}
