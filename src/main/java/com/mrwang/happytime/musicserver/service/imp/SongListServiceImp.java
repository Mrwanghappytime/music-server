package com.mrwang.happytime.musicserver.service.imp;

import com.mrwang.happytime.musicserver.configuration.FilePathConfiguration;
import com.mrwang.happytime.musicserver.dao.SongListDao;
import com.mrwang.happytime.musicserver.po.SongList;
import com.mrwang.happytime.musicserver.service.SongListService;
import com.mrwang.happytime.musicserver.util.MultipartFileUtils;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class SongListServiceImp implements SongListService {
    @Autowired
    private SongListDao songListDao;
    @Autowired
    private FilePathConfiguration configuration;
    @Override
    public List<SongList> getAll() {
        try{
            List<SongList> songLists = songListDao.selectAll();
            return songLists;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SongList> getSongListsByTitle(SongList songList) {
        try{
            List<SongList> songLists = songListDao.selectSongLists(songList);
            return songLists;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SongList> getSongListsByTitleLike(SongList songList) {
        try{
            String like = songList.getTitle();
            songList.setTitle("%" + like + "%");
            List<SongList> songLists = songListDao.selectSongListsByTitleLike(songList);
            return songLists;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SongList> getSongByStyle(SongList songList) {
        try{
            List<SongList> songLists = songListDao.selectSongLists(songList);
            return songLists;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteSongList(SongList songList) {
        try {
            int count = songListDao.delete(songList);
            return count > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Result updateSongList(SongList songList) {
        try{
            int count = songListDao.update(songList);
            return count > 0 ? Result.getResult(1,"更新歌单1成功"):Result.getResult(0,"更新歌单1失败");

        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0,"更新歌单1失败");
        }
    }

    @Override
    public Result updateSongListImg(SongList songList, MultipartFile file) {
        String avatar = null,url = null;
        try{
            avatar ="/img/songListPic/" +  songList.getId() + "_pic." + MultipartFileUtils.getFileType(file);
            url = configuration.getPath() + avatar;
            songList.setPic(avatar);
            file.transferTo(new File(url));
            int count = songListDao.update(songList);
            return count > 0 ? Result.getResult(1,"更新歌单1成功"):Result.getResult(0,"更新歌单1失败");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.getResult(0,"更新歌单1失败");
        }
    }

    @Override
    public List<SongList> getSongListsById(SongList songList) {
        try{
            return songListDao.selectSongLists(songList);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
