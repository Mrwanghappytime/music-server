package com.mrwang.happytime.musicserver.service.imp;

import com.mrwang.happytime.musicserver.dao.CommentDao;
import com.mrwang.happytime.musicserver.po.Comment;
import com.mrwang.happytime.musicserver.service.CommentService;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Override
    public List<Comment> findAll() {
        try{
            List<Comment> comments = commentDao.getAll();
            return comments;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Result insert(Comment comment) {
        try{
            int count = commentDao.insert(comment);
            return count > 0 ? Result.getResult(1,"点赞成功") : Result.getResult(0,"点赞失败");
        }catch (Exception e){
            return Result.getResult(0,"点赞失败");
        }
    }

    @Override
    public List<Comment> getSongDetail(Comment comment) {
        try{
            List<Comment> comments = commentDao.getCommentsByComment(comment);
            return comments;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Comment> getsongListDetail(Comment comment) {
        try{
            List<Comment> comments = commentDao.getCommentsByComment(comment);
            return comments;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Result updateCommentUp(Comment comment) {
        try{
            int count = commentDao.updateComment(comment);
            return count > 0 ? Result.getResult(1,"修改成功") : Result.getResult(0,"修改失败");
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0,"修改失败");
        }

    }

    @Override
    public boolean deleteComment(Comment comment) {
        try{
            int count = commentDao.deleteComment(comment);
            return count > 0 ? true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Result updateComment(Comment comment) {
        try{
            int count = commentDao.updateComment(comment);
            return count > 0 ? Result.getResult(1,"修改成功") : Result.getResult(0,"修改失败");
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0,"修改失败");
        }
    }
}
