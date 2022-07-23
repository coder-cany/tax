package com.hs.user.controller;

import com.hs.common.vo.Response;
import com.hs.user.exception.UserVerifyException;
import com.hs.user.po.User;
import com.hs.user.service.UserService;
import com.hs.user.util.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@RestController
@Slf4j
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/register")
    public Response register(@RequestBody User user){
        try {
            long time = System.currentTimeMillis();
            if (StringUtils.isEmpty(user.getPassword())) {
                throw new UserVerifyException("Password should not be empty.");
            }
            if (StringUtils.isEmpty(user.getCreateTime())) {
                user.setCreateTime(LocalDateTime.now());
            }
            user.setPassword(Md5Utils.md5DigestAsHex(user.getId(), user.getPassword()));
            userService.register(user);
            log.debug(user.getId() + " Register use time: " + (System.currentTimeMillis() - time) + "ms");
            return Response.success();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Response.fail("注册失败");
        }
    }

    @PostMapping("/login")
    public Response<String> login(@RequestParam("id") @NotNull Integer id,
                                  @RequestParam("pwd") @NotNull String pwd){
        log.debug(id + " user start to login.");
        try {
            String token = userService.login(id, pwd);
            return Response.success(token);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
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
