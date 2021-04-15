package com.mrwang.happytime.musicserver.controller;

import com.mrwang.happytime.musicserver.annotation.ParameterCheck;
import com.mrwang.happytime.musicserver.po.Rank;
import com.mrwang.happytime.musicserver.service.RankService;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rank")
public class RankController {
    @Autowired
    private RankService rankService;
    //songListId&consumerId&score
    @PostMapping("/add")
    @ParameterCheck("songListId")
    @ParameterCheck("consumerId")
    @ParameterCheck("score")
    public Result add(Rank rank){
        return rankService.insertRank(rank);
    }
    @GetMapping("")
    @ParameterCheck("songListId")
    public int root(Rank rank){
        return rankService.getAvgRankOfSongList(rank);
    }


}
