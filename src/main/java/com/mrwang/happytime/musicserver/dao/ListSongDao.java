package com.mrwang.happytime.musicserver.dao;

import com.mrwang.happytime.musicserver.po.ListSong;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ListSongDao {
    List<ListSong> selectAll();

    int insert(ListSong listSong);

    List<ListSong> selectByListSong(ListSong listSong);

    int delete(ListSong listSong);
}
