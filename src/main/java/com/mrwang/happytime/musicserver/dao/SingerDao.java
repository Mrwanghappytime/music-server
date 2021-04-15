package com.mrwang.happytime.musicserver.dao;

import com.mrwang.happytime.musicserver.po.Singer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingerDao {
    int insert(Singer singer);

    List<Singer> selectAll();

    List<Singer> selectSinger(Singer singer);

    int delete(Singer singer);

    int update(Singer singer);
}
