package com.hs.start.controller;

import com.hs.common.entity.User;
import com.hs.common.vo.Response;
import com.hs.start.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Response register(@RequestBody User user){
        log.info("ready to register new user.");
        Response<Integer> response = userService.register(user);
        log.info("register success."+user.toString());
        return response;
    }

    @GetMapping("/login")
    public Response<Object> login(@RequestBody User user){
        return userService.login(user);
    }

    @GetMapping("/getInfo/{account}")
    public Response<User> getInfo(@PathVariable Integer account){
        return userService.getInfo(account);
    }
}
