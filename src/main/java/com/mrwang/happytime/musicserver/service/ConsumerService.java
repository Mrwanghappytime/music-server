package com.mrwang.happytime.musicserver.service;

import com.mrwang.happytime.musicserver.po.Consumer;
import com.mrwang.happytime.musicserver.vo.Result;
import com.mrwang.happytime.musicserver.vo.ResultLogin;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ConsumerService {
    List<Consumer> getAll();

    ResultLogin findConsumerByUserPassword(Consumer consumer);

    Result addConsumer(Consumer consumer);

    List<Consumer> findConsumerById(Consumer consumer);

    boolean deleteConsumerById(Consumer consumer);

    Result updateConsumer(Consumer consumer);

    Result avatarUpdate(MultipartFile file, Consumer consumer);
}
