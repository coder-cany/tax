package com.hs.order.service;

import com.hs.order.dao.BusinessDao;
import com.hs.common.entity.Business;
import com.hs.common.entity.User;
import com.hs.order.redis.RedisService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

public class BusinessService {
    @Resource
    private BusinessDao taskDao;
    @Resource
    private RedisService redisService;

    public List<Business> getBusiness(String token) {
        return taskDao.getBusiness();
    }

    public void createBusiness(String token, String nextPerson, String orderId, String service) {

        User preUser = redisService.get(token, User.class);
        taskDao.createBusiness(orderId, service, preUser.getAccount(), nextPerson, LocalDateTime.now());
    }
}
