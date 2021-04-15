package com.mrwang.happytime.musicserver.service.imp;

import com.mrwang.happytime.musicserver.dao.RankDao;
import com.mrwang.happytime.musicserver.po.Rank;
import com.mrwang.happytime.musicserver.service.RankService;
import com.mrwang.happytime.musicserver.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RankServiceImp implements RankService {
    @Autowired
    private RankDao rankDao;
    @Override
    public Result insertRank(Rank rank) {
        try{
            int count = rankDao.insert(rank);
            return count > 0 ? Result.getResult(1,"评论成功"):Result.getResult(0,"评论失败");
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0,"评论失败");
        }
    }

    @Override
    public int getAvgRankOfSongList(Rank rank) {
        int grade = 0;
        try{
           grade = rankDao.getAvgRank(rank);
           return grade;

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
