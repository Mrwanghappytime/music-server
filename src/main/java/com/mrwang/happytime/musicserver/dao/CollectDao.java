package com.mrwang.happytime.musicserver.dao;

import com.mrwang.happytime.musicserver.po.Collect;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectDao {
    List<Collect> getAll();

    List<Collect> getCollects(Collect collect);

    int delete(Collect collect);

    int update(Collect collect);

    int addCollect(Collect collect);
}
