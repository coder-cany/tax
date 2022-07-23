package com.hs.user.consume;

import com.hs.user.cache.UserToInsertDBQueue;
import com.hs.user.dao.UserDao;
import com.hs.user.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ConsumeInsertDB {
    public void insertDB(UserDao userDao) {
        new Thread(() -> {
            while (true) {
                User user = UserToInsertDBQueue.consumeToInsert();
                if (user == null) {
//                    log.info("wait...");
                    try {
                        Thread.sleep(180000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
//                    log.info(Thread.currentThread().getName() + ": start to insert user.");
                    long l = System.currentTimeMillis();
                    try {
                        userDao.insertUser(user);
                        log.info(Thread.currentThread().getName() + ": " + user.getId() + " inserted. Use ms time: " + (System.currentTimeMillis() - l));
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
            }
        }).start();
    }
}
