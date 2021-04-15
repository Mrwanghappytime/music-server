package com.mrwang.happytime.musicserver.dao;

import com.mrwang.happytime.musicserver.po.Rank;
import org.springframework.stereotype.Repository;

@Repository
public interface RankDao {
    int insert(Rank rank);

    int getAvgRank(Rank rank);
}
