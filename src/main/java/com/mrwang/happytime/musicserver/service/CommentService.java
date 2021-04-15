package com.mrwang.happytime.musicserver.service;

import com.mrwang.happytime.musicserver.po.Comment;
import com.mrwang.happytime.musicserver.vo.Result;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();

    Result insert(Comment comment);

    List<Comment> getSongDetail(Comment comment);

    List<Comment> getsongListDetail(Comment comment);

    Result updateCommentUp(Comment comment);

    boolean deleteComment(Comment comment);

    Result updateComment(Comment comment);
}
