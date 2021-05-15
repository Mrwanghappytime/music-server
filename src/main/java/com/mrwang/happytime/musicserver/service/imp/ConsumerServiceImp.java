package com.mrwang.happytime.musicserver.service.imp;

import com.mrwang.happytime.musicserver.configuration.FilePathConfiguration;
import com.mrwang.happytime.musicserver.dao.ConsumerDao;
import com.mrwang.happytime.musicserver.po.Consumer;
import com.mrwang.happytime.musicserver.service.ConsumerService;
import com.mrwang.happytime.musicserver.shiro.token.LoginTypeUsernamePasswordToken;
import com.mrwang.happytime.musicserver.util.MD5Encode;
import com.mrwang.happytime.musicserver.util.MultipartFileUtils;
import com.mrwang.happytime.musicserver.vo.Result;
import com.mrwang.happytime.musicserver.vo.ResultLogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
@Service
@Transactional
public class ConsumerServiceImp implements ConsumerService {
    @Autowired
    private FilePathConfiguration configuration;

    @Autowired
    private ConsumerDao consumerDao;
    @Override
    public List<Consumer> getAll() {
        try {
            List<Consumer> consumers = consumerDao.selectAll();
            return consumers;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 需要重写逻辑
     * @param consumer
     * @return
     */
    @Override
    public ResultLogin findConsumerByUserPassword(Consumer consumer) {
        List<Consumer> consumer1 = null;
        String password = consumer.getPassword();
        consumer.setPassword(null);
        consumer1 = consumerDao.selectConsumerByCommer(consumer);
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()) {
            LoginTypeUsernamePasswordToken loginTypeUsernamePasswordToken = new LoginTypeUsernamePasswordToken(consumer.getUsername(), password, "consumer");
            subject.login(loginTypeUsernamePasswordToken);

            if(consumer1.size() != 0){
                //session.setAttribute("user",consumer1.get(0));
                return new ResultLogin(1,"登录成功",consumer1);
            }
            return new ResultLogin(0,"用户名或者密码错误");

        }
        return new ResultLogin(1,"登录成功",consumer1);
    }

    @Override
    public Result addConsumer(Consumer consumer) {
        try{
            consumer.setPassword(MD5Encode.encode(consumer.getUsername(),consumer.getPassword()));
            int count = consumerDao.insetConsumer(consumer);
            return count>0?Result.getResult(1,"添加用户成功"):Result.getResult(0,"添加用户失败");
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0,"添加用户失败");
        }
    }

    @Override
    public List<Consumer> findConsumerById(Consumer consumer) {
        try{
            List<Consumer> consumers = consumerDao.selectConsumerByCommer(consumer);
            return consumers;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteConsumerById(Consumer consumer) {
        try{

            int count = consumerDao.deleteConsumer(consumer);
            return count > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Result updateConsumer(Consumer consumer) {
        try{
            int count = consumerDao.updateConsumer(consumer);
            return count>0?Result.getResult(1,"更新用户成功"):Result.getResult(0,"更新用户失败");
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0,"更新用户失败");
        }
    }

    @Override
    public Result avatarUpdate(MultipartFile file, Consumer consumer) {
        try{
            String path = null;
            String avatar = null;
            if(!file.isEmpty()){
                avatar = "/img/user/"  + consumer.getId() + "_avatar." + MultipartFileUtils.getFileType(file);
                path = configuration.getPath() +  avatar;
                file.transferTo(new File(path));
            }
            consumer.setAvator(avatar);
            int count = consumerDao.updateConsumer(consumer);
            if(count > 0) {
                return Result.getResult(1,"更新成功",avatar);
            }else{
                return Result.getResult(0,"更新失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0,"更新失败");
        }
    }
}
