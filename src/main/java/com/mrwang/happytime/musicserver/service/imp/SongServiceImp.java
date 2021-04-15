package com.mrwang.happytime.musicserver.service.imp;

import com.mrwang.happytime.musicserver.configuration.FilePathConfiguration;
import com.mrwang.happytime.musicserver.dao.SongDao;
import com.mrwang.happytime.musicserver.po.Song;
import com.mrwang.happytime.musicserver.service.SongService;
import com.mrwang.happytime.musicserver.util.MultipartFileUtils;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@Transactional
public class SongServiceImp implements SongService {
    @Autowired
    private SongDao songDao;
    @Autowired
    private FilePathConfiguration configuration;
    @Override
    public List<Song> getAllSong() {
        try{
            List<Song> songs = songDao.selectAll();
            return songs;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Song> getSongDetail(Song song) {
        try{
            List<Song> songs = songDao.selectSongs(song);
            return songs;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Song> getSongBySingerId(Song song) {
        try{
            List<Song> songs = songDao.selectSongs(song);
            return songs;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Song> getSongBySingerName(Song song) {
        try{
            List<Song> songs = songDao.selectSongsBySingerName(song);
            return songs;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteSong(Song song) {
        try{
            int count = songDao.delete(song);
            return count > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Song> getSongBySongName(Song song) {
        try{
            List<Song> songs = songDao.selectSongs(song);
            return songs;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Result updateSong(Song song) {
        try{
            int count = songDao.update(song);
            return count > 0 ? Result.getResult(1,"更新歌曲成功"):Result.getResult(0,"更新歌曲失败");
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0,"更新歌曲失败");
        }
    }

    @Override
    public Result updateSongImg(Song song, MultipartFile file) {
        String avatar = null, url = null;
        try{
            if(!file.isEmpty()){
                avatar = "/img/songPic/" + song.getId() + "_pic." + MultipartFileUtils.getFileType(file);
                url = configuration.getPath() + avatar;
                file.transferTo(new File(url));
            }
            song.setPic(avatar);
            int count = songDao.update(song);
            return count > 0 ? Result.getResult(1,"更新歌曲图片成功"):Result.getResult(0,"更新歌曲图片失败");
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.getResult(0,"更新歌曲图片失败");
    }

    @Override
    public Result updateSongUrl(Song song, MultipartFile file) {
        String avatar = null, url = null;
        try{
            if(!file.isEmpty()){
                avatar = "/song/"  + file.getOriginalFilename();
                url = configuration.getPath() + avatar;
                file.transferTo(new File(url));
            }
            song.setUrl(avatar);
            int count = songDao.update(song);
            return count > 0 ? Result.getResult(1,"更新歌曲URL成功"):Result.getResult(0,"更新歌曲URL失败");
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.getResult(0,"更新歌曲URL失败");
    }
}
