package com.mrwang.happytime.musicserver.service;

import com.mrwang.happytime.musicserver.po.Collect;
import com.mrwang.happytime.musicserver.vo.Result;

import java.util.List;

public interface CollectService {
    List<Collect> getAllCollect();

    List<Collect> detail(Collect collect);

    boolean delete(Collect collect);

    Result updateCollect(Collect collect);

    Result addCollect(Collect collect);
}
