package com.hs.order.controller;

import com.hs.common.entity.User;
import com.hs.order.exception.SimpleException;
import com.hs.order.service.UserService;
import com.hs.common.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    //处理登录请求
    @PostMapping("/login")
    public Response login(@RequestParam("account") int account, @RequestParam("password") String password,
                          HttpServletRequest request, HttpServletResponse response) {
        try {
            //尝试登录操作，成功则返回ok
            Map map = userService.login(account, password, request, response);
            log.info(account + " is logging");
            return Response.success(map);
        } catch (SimpleException e) {
            //登录失败，则获取自定义的失败信息
            return Response.fail(e.getMessage());
        }
    }

    //处理注销请求
    @GetMapping("/logout")
    public Response logout(@RequestParam("token") String token) {
        try {
            userService.logout(token);
            return Response.success();
        } catch (SimpleException e) {
            return Response.fail(e.getMessage());
        }
    }

    // 处理注册请求
    @PostMapping("/register")
    public Response register(User user) {
        try {
            System.out.println(user.toString());
            userService.registerService(user);
            return Response.success();
        } catch (SimpleException e) {
            return Response.fail(e.getMessage());
        }
    }

    //查用户信息
    @GetMapping("/UserInfo/{account}")
    @ResponseBody
    public Response getUserInfo(@PathVariable(value = "account", required = false) Integer account) {
        //返回user对象
        if (account == null) {
            return Response.fail("未登录");
        }
        return Response.success(userService.getUserInfo(account));
    }

    //查用户信息
    @GetMapping("/myInfo")
    public Response getMyInfo(@RequestParam(value = "token", required = false) String token) {
        //返回user对象
        try {
            return Response.success(userService.getUserInfo(token));
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }

    }

    //处理修改信息请求
    @PutMapping("/myInfo")
    public Response updateInfo(@RequestParam(value = "token") String token,
                               @RequestParam(value = "account") int account,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "password", required = false) String password,
                               @RequestParam(value = "sex", required = false) Integer sex,
                               @RequestParam(value = "img", required = false) MultipartFile file) {
        try {
            User user = new User();
            user.setAccount(account);
            user.setName(name);
            user.setPassword(password);
            user.setSex(sex);
            userService.updateInfo(token, user, file);
            return Response.success();
        } catch (SimpleException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

}
