package com.hs.user.controller;

import com.hs.common.vo.Response;
import com.hs.user.po.User;
import com.hs.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/register")
    public Response register(@RequestBody User user){
        try {
            log.info(user.toString());
            return userService.register(user);
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
            return Response.fail("注册失败");
        }
    }

    @PostMapping("/login")
    public Response<Object> login(@RequestParam("id") Integer id,
                                  @RequestParam("pwd") String pwd){
        return userService.login(id, pwd);
    }

    @GetMapping("/{account}")
    public Response<User> getInfo(@PathVariable Integer account){
        return userService.getInfo(account);
    }

    @PutMapping("/user/settings")
    public Response updateInfo(@RequestBody User user){
        userService.updateInfo(user);
        return  Response.success();
    }
}
