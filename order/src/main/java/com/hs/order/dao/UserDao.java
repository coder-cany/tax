package com.hs.order.dao;


import com.hs.common.entity.User;
import com.hs.order.mybatisprovider.UserUpdateProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    @Insert("insert into user values (#{user.account,javaType=int},#{user.password},#{user.name},#{user.role}," +
            "#{user.sex},#{user.createTime},#{user.img})")
    void addUser(User user);

    @UpdateProvider(type = UserUpdateProvider.class, method = "update")
    void updateUser(User user);

    @Select("select account,name,img from user where name like concat (#{name},'%')")
    List<User> findUserByName(String name);

    @Select("select account,password,name,role,sex,img from user where account=#{account,javaType=int}")
    User findUserByAccount(Integer account);

    @Select("select role_id from role where role_id=#{role}")
    Integer checkRole(Integer role);
}
