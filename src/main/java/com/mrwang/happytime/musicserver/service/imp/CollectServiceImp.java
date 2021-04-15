package com.mrwang.happytime.musicserver.service.imp;

import com.mrwang.happytime.musicserver.dao.CollectDao;
import com.mrwang.happytime.musicserver.po.Collect;
import com.mrwang.happytime.musicserver.service.CollectService;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CollectServiceImp implements CollectService {
    @Autowired
    private CollectDao collectDao;

    @Override
    public List<Collect> getAllCollect() {
        try{
            return collectDao.getAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Collect> detail(Collect collect) {
        try{
            return collectDao.getCollects(collect);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Collect collect) {
        try{
            int count = collectDao.delete(collect);
            return ((count > 0) ? true:false);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Result updateCollect(Collect collect) {
        try{
            int count = collectDao.update(collect);
            return count > 0 ? Result.getResult(1,"更新歌单成功") : Result.getResult(0,"更新歌单失败");
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0,"更新歌单失败");
        }
    }

    @Override
    public Result addCollect(Collect collect) {
        try{
            int count = collectDao.addCollect(collect);
            return count > 0 ? Result.getResult(1,"添加歌曲成功") : Result.getResult(0,"添加歌曲失败");
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0,"添加歌曲失败");
        }
    }
}
