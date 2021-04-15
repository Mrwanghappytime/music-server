package com.mrwang.happytime.musicserver.po;


import com.mrwang.happytime.musicserver.annotation.MybatisMap;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
@ToString
@Data
public class Collect {
    @MybatisMap("id")
    private Integer id;
    @MybatisMap("user_id")
    private Integer userId;
    @MybatisMap("type")
    private Byte type;
    @MybatisMap("song_id")
    private Integer songId;
    @MybatisMap("song_list_id")
    private Integer songListId;
    @MybatisMap("create_time")
    private Date createTime;


}