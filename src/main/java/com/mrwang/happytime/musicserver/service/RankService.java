package com.mrwang.happytime.musicserver.service;

import com.mrwang.happytime.musicserver.po.Rank;
import com.mrwang.happytime.musicserver.vo.Result;

public interface RankService {
    Result insertRank(Rank rank);

    int getAvgRankOfSongList(Rank rank);
}
