package com.mrwang.happytime.musicserver.dao;

import com.mrwang.happytime.musicserver.po.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    List<Comment> getAll();

    int insert(Comment comment);

    List<Comment> getCommentsByComment(Comment comment);

    int updateComment(Comment comment);

    int deleteComment(Comment comment);
}
