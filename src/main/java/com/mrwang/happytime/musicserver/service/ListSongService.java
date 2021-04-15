package com.mrwang.happytime.musicserver.service;

import com.mrwang.happytime.musicserver.po.ListSong;
import com.mrwang.happytime.musicserver.vo.Result;

import java.util.List;

public interface ListSongService {
    List<ListSong> getAllListSong();

    Result insertListSong(ListSong listSong);

    List<ListSong> getListSongBySongListId(ListSong listSong);

    boolean deleteListSong(ListSong listSong);
}
