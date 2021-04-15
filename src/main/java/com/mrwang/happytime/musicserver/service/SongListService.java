package com.mrwang.happytime.musicserver.service;

import com.mrwang.happytime.musicserver.po.SongList;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SongListService {
    List<SongList> getAll();

    List<SongList> getSongListsByTitle(SongList songList);

    List<SongList> getSongListsByTitleLike(SongList songList);

    List<SongList> getSongByStyle(SongList songList);

    boolean deleteSongList(SongList songList);

    Result updateSongList(SongList songList);

    Result updateSongListImg(SongList songList, MultipartFile file);

    List<SongList> getSongListsById(SongList songList);
}
