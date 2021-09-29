package com.hs.user.dao;

import com.hs.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    Integer insertUser(User user);
    User getUserById(Integer id);
    User getUserInfo(Integer id);
}
