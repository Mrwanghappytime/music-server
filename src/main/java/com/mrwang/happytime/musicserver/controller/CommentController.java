package com.mrwang.happytime.musicserver.controller;

import com.mrwang.happytime.musicserver.annotation.ParameterCheck;
import com.mrwang.happytime.musicserver.po.Comment;
import com.mrwang.happytime.musicserver.service.CommentService;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("")
    public List<Comment> root(){
        return commentService.findAll();
    }

    @PostMapping("add")
    @ParameterCheck("userId")
    @ParameterCheck("type")
    @ParameterCheck("songListId")
    @ParameterCheck("songId")
    @ParameterCheck("content")
    public Result add(Comment comment){
        return commentService.insert(comment);
    }
    @ParameterCheck("songId")
    @GetMapping("/song/detail")
    public List<Comment> songDetail(Comment comment){
        return commentService.getSongDetail(comment);
    }
    @ParameterCheck("songListId")
    @GetMapping("/songList/detail")
    public List<Comment> songListDetail(Comment comment){
        return commentService.getsongListDetail(comment);
    }
    /**
     * 此接口后续需进行修改，防止一个用户多次点赞
     */
    @ParameterCheck("id")
    @ParameterCheck("up")
    @PostMapping("/like")
    public Result commentLike(Comment comment){

        return commentService.updateCommentUp(comment);

    }
    @ParameterCheck("id")
    @GetMapping("/delete")
    public boolean delete(Comment comment){
        return commentService.deleteComment(comment);
    }
//    userId&type&songListId&songId&content&type&up
    @ParameterCheck("id")
    @ParameterCheck(value = "type",notNull = false)
    @ParameterCheck(value = "songListId",notNull = false)
    @ParameterCheck(value = "type",notNull = false)
    @ParameterCheck(value = "songId",notNull = false)
    @ParameterCheck(value = "content",notNull = false)
    @ParameterCheck(value = "up",notNull = false)
    @PostMapping("/update")
    public Result updateComment(Comment comment){
        return commentService.updateComment(comment);
    }
















}
