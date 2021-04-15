package com.mrwang.happytime.musicserver.dao;

import com.mrwang.happytime.musicserver.po.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongDao {
    List<Song> selectAll();

    List<Song> selectSongs(Song song);

    List<Song> selectSongsBySingerName(Song song);

    int delete(Song song);

    int update(Song song);
}
