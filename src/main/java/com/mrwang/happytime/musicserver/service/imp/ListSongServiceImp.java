package com.mrwang.happytime.musicserver.service.imp;

import com.mrwang.happytime.musicserver.dao.ListSongDao;
import com.mrwang.happytime.musicserver.po.ListSong;
import com.mrwang.happytime.musicserver.service.ListSongService;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ListSongServiceImp implements ListSongService {
    @Autowired
    private ListSongDao listSongDao;

    @Override
    public List<ListSong> getAllListSong() {
        return listSongDao.selectAll();
    }

    @Override
    public Result insertListSong(ListSong listSong) {
        try{
            int count = listSongDao.insert(listSong);
            return count > 0 ? Result.getResult(1,"添加成功"):Result.getResult(0,"添加失败");
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0,"添加失败");
        }
    }

    @Override
    public List<ListSong> getListSongBySongListId(ListSong listSong) {
        return listSongDao.selectByListSong(listSong);
    }

    @Override
    public boolean deleteListSong(ListSong listSong) {
        try{
            int count = listSongDao.delete(listSong);
            return count > 0;
        }catch (Exception e){
            return false;
        }
    }


}
