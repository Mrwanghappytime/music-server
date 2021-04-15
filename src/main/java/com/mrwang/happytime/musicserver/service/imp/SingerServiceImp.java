package com.mrwang.happytime.musicserver.service.imp;

import com.mrwang.happytime.musicserver.configuration.FilePathConfiguration;
import com.mrwang.happytime.musicserver.dao.SingerDao;
import com.mrwang.happytime.musicserver.po.Singer;
import com.mrwang.happytime.musicserver.service.SingerService;
import com.mrwang.happytime.musicserver.util.MultipartFileUtils;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class SingerServiceImp implements SingerService {
    @Autowired
    private FilePathConfiguration filePathConfiguration;
    @Autowired
    private SingerDao singerDao;
    @Override
    public Result insertSinger(Singer singer) {
        try{
            int count = singerDao.insert(singer);
            return count>0?Result.getResult(1,"添加歌手成功"):Result.getResult(0,"添加歌手失败");
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0,"添加歌手失败");
        }
    }

    @Override
    public List<Singer> getAllSinger() {
        try{
            List<Singer> singers = singerDao.selectAll();
            return singers;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Singer> getSingerByName(Singer singer) {
        try{
            List<Singer> singers = singerDao.selectSinger(singer);
            return singers;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Singer> getSingerBySex(Singer singer) {
        try{
            List<Singer> singers = singerDao.selectSinger(singer);
            return singers;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Singer singer) {
        try{
            int count = singerDao.delete(singer);
            return count > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Result updateSinger(Singer singer) {
        try{
            int count = singerDao.update(singer);
            return count > 0 ? Result.getResult(1,"更新歌手成功"):Result.getResult(0,"更新歌手失败");
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0,"更新歌手失败");
        }
    }

    @Override
    public Result<String> updateSingerAvatar(Singer singer, MultipartFile file) {
        String url = null;
        String path = null;
        try{
            if(!file.isEmpty()) {
                url = "singerPic" + singer.getId() + "_avatar." + MultipartFileUtils.getFileType(file);
                path = filePathConfiguration.getPath() + "\\img\\" + url;
                singer.setPic(path);
                int count = singerDao.update(singer);
                return count > 0 ? Result.getResult(1,"更新歌手成功",path):Result.getResult(0,"更新歌手失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.getResult(0,"更新歌手失败");
    }
}
