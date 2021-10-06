package com.hs.user.dao;

import com.hs.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    Integer insertUser(@Param("user") User user);

    User getUserById(@Param("id") Integer id);

    User getUserInfo(@Param("id") Integer id);
}
