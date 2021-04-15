package com.mrwang.happytime.musicserver.po;

import com.mrwang.happytime.musicserver.annotation.MybatisMap;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@ToString
@Data
public class Rank implements Serializable {
    @MybatisMap("id")
    private Long id;
    @MybatisMap("song_list_id")
    private Long songListId;
    @MybatisMap("consumer_id")
    private Long consumerId;
    @MybatisMap("score")
    private Integer score;


}