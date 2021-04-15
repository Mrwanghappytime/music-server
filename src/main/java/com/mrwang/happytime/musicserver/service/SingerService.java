package com.mrwang.happytime.musicserver.service;

import com.mrwang.happytime.musicserver.po.Singer;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SingerService {
    Result insertSinger(Singer singer);

    List<Singer> getAllSinger();

    List<Singer> getSingerByName(Singer singer);

    List<Singer> getSingerBySex(Singer singer);

    boolean delete(Singer singer);

    Result updateSinger(Singer singer);

    Result<String> updateSingerAvatar(Singer singer, MultipartFile file);
}
