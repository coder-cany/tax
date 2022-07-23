package com.hs.user.config;

import com.hs.user.consume.ConsumeInsertDB;
import com.hs.user.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class DBConfig {
    @Resource
    UserDao userDao;

    @Bean
    public void insertDB() {
        ConsumeInsertDB consumeInsertDB = new ConsumeInsertDB();
        for (int i = 0; i < 1000; i++) {
            consumeInsertDB.insertDB(userDao);
        }
    }
}
