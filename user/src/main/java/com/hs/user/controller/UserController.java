package com.hs.user.controller;

import com.hs.common.entity.User;
import com.hs.common.vo.Response;
import com.hs.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/register")
    public Response register(User user){
        try {
            return userService.register(user);
        }catch (Exception e) {
            log.error(e.getStackTrace().toString());
            return Response.fail();
        }
    }

    @PostMapping("/login")
    public Response<Object> login(User user){
        return userService.login(user);
    }

    @GetMapping("/getInfo")
    public Response<User> getInfo(Integer account){
        return userService.getInfo(account);
    }
}
