package com.hs.user.service;

import com.hs.common.vo.Response;
import com.hs.user.dao.UserDao;
import com.hs.user.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserService {
    @Resource
    UserDao userDao;

    public void register(User user) {
        log.info("start to insert user:" + user.toString());
        userDao.insertUser(user);
        log.info("insert success.");
    }

    public Response<Object> login(Integer id, String pwd){
        User user = userDao.getUserByIdAndPwd(id,pwd);

        return Response.success();
    }
    public Response<User> getInfo(Integer id){
        return Response.success(userDao.getUserInfo(id));
    }

    public int updateInfo(User user) {
        return userDao.updateUser(user);
    }
}
