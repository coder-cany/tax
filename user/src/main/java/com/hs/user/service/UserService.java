package com.hs.user.service;

import com.hs.common.entity.User;
import com.hs.common.vo.Response;
import com.hs.user.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    UserDao userDao;

    public Response register(User user) throws Exception {
        int i = userDao.insertUser(user);
        if (i == 0){
            throw new Exception("insert user fail. user id = "+user.getId());
        }
        return Response.success();
    }
    public Response<Object> login(User user){
        return Response.success(userDao.getUserById(user.getId()));
    }
    public Response<User> getInfo(Integer id){
        return Response.success(userDao.getUserInfo(id));
    }
}
