package com.hs.start.service;

import com.hs.common.entity.User;
import com.hs.common.vo.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user",url = "http://localhost:2002")
public interface UserService {
    @PostMapping("/register")
    Response<Integer> register(User user);
    @PostMapping("/login")
    Response<Object> login(User user);
    @GetMapping("/getInfo")
    Response<User> getInfo(Integer account);
}
