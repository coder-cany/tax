package com.hs.user.dao;

import com.hs.user.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    Integer insertUser(@Param("user") User user);

    User getUserById(@Param("id") Integer id);

    User getUserInfo(@Param("id") Integer id);

    int updateUser(User user);

    User getUserByIdAndPwd(Integer id, String pwd);
}
