package com.mrwang.happytime.musicserver.po;


import com.mrwang.happytime.musicserver.annotation.MybatisMap;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@ToString
@Data
public class ListSong implements Serializable {
    @MybatisMap("id")
    private Integer id;
    @MybatisMap("song_id")
    private Integer songId;
    @MybatisMap("song_list_id")
    private Integer songListId;
}
