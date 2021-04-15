package com.mrwang.happytime.musicserver.dao;

import com.mrwang.happytime.musicserver.po.Consumer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerDao {

    List<Consumer> selectAll();

    List<Consumer> selectConsumerByCommer(Consumer consumer);

    int insetConsumer(Consumer consumer);

    int deleteConsumer(Consumer consumer);

    int updateConsumer(Consumer consumer);
}
