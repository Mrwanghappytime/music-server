package com.mrwang.happytime.musicserver.service;

import com.mrwang.happytime.musicserver.po.Song;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SongService {
    List<Song> getAllSong();

    List<Song> getSongDetail(Song song);

    List<Song> getSongBySingerId(Song song);

    List<Song> getSongBySingerName(Song song);

    boolean deleteSong(Song song);

    List<Song> getSongBySongName(Song song);

    Result updateSong(Song song);

    Result updateSongImg(Song song, MultipartFile file);

    Result updateSongUrl(Song song, MultipartFile file);
}
