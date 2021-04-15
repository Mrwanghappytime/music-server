package com.mrwang.happytime.musicserver.dao;

import com.mrwang.happytime.musicserver.po.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongListDao {

    List<SongList> selectAll();

    List<SongList> selectSongLists(SongList songList);

    List<SongList> selectSongListsByTitleLike(SongList songList);

    int delete(SongList songList);

    int update(SongList songList);
}
