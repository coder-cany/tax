package com.hs.order.service;

import com.hs.order.dao.UserDao;
import com.hs.common.entity.User;
import com.hs.order.exception.SimpleException;
import com.hs.order.redis.RedisService;
import com.hs.order.utils.CheckToken;
import com.hs.order.utils.CookieUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    private UserDao userDao;
    private final RedisService redisService;


    public UserService(UserDao userDao, RedisService redisService) {
        this.userDao = userDao;
        this.redisService = redisService;
    }

    /**
     * 每次登陆操作如果账号密码匹配，就会生成一个新的token，
     * 并且从本次http请求中检查是否已有token。有，则尝试删除缓存redis中旧的token键值对。
     * 最后添加新token的缓存，以及返回客户端新的token
     *
     * @param account  账号
     * @param password 密码
     * @param request  http请求
     * @param response http响应
     */
    public Map login(Integer account, String password, HttpServletRequest request,
                     HttpServletResponse response) {
        User user = userDao.findUserByAccount(account);
        if (user != null) {
            if (user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
                //生成token
                String token = UUID.randomUUID().toString();
                //如果请求中已有token则尝试删除缓存中对应的token
                Cookie oldCookie = CookieUtils.getCookieByName(request, "token");
                if (oldCookie != null)
                    redisService.delete(oldCookie.getValue());
                //添加新token到缓存中
                user.setPassword(null);
                redisService.set(token, user);
                redisService.expire(token, 60 * 60);
                //返回cookie 有效时间30天,
                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(30 * 24 * 60 * 60);
                response.addCookie(cookie);
                HashMap map = new HashMap();
                map.put("token", token);
                map.put("user", user);

                return map;
            } else
                throw new SimpleException("密码不正确");
        } else
            throw new SimpleException("账号不存在");
    }

    /**
     * 用户离线原因有二：一，一小时内没有携带token发起任何请求以至于redis的token失效；
     * 二，用户主动退出登录是redis的token失效
     * 此方法用于用户的退出登录操作
     *
     * @param token
     */
    public void logout(String token) {
        //如果携带的token在服务器上找到对应缓存，则说明用户在线
        if (redisService.exists(token)) {
            redisService.delete(token);
        } else {
            throw new SimpleException("未登录");
        }
    }

    public void registerService(User user) {
        LocalDateTime creatDate = LocalDateTime.now();

        if (userDao.findUserByAccount(user.getAccount()) != null) {
            throw new SimpleException("账户已存在");
        } else {
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            user.setCreateTime(creatDate);
            user.setImg("default.jpg");
            userDao.addUser(user);
        }
    }

    public User getUserInfo(int account) {
        User user = userDao.findUserByAccount(account);
        user.setPassword("");
        return user;
    }

    public User getUserInfo(String token) {
        if (redisService.exists(token)) {
            User user = redisService.get(token, User.class);
            return user;
        } else {
            throw new SimpleException("未登录");
        }
    }

    public void updateInfo(String token, User user, MultipartFile file) {
        if (CheckToken.check(redisService, token, user.getAccount())) {
            if (file != null) {
                try {
                    String fileName = file.getOriginalFilename();
                    File newFile = new File(System.getProperty("user.dir") + "/src/main/resources/static/usericon/" + fileName);
                    file.transferTo(newFile);
                    user.setImg(fileName);
                } catch (IOException e) {
                    throw new SimpleException("头像上传出现了BUG");
                }
            }
            if (user.getPassword() != null) {
                user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            }
            if (user.getRole() != null && userDao.checkRole(user.getRole()) == null) {
                throw new SimpleException("role error");
            }
            userDao.updateUser(user);
        } else {
            throw new SimpleException("未登录");
        }
    }

//    public void updatePoints(String token,int account,int points){
//        if(!CheckToken.check(redisService,token,account)){
//            userDao.updatePoints(account,points);
//        }else {
//            throw new SimpleException("未登录");
//        }
//    }

    public List<User> findUserByName(String name) {
        List<User> user = userDao.findUserByName(name);
        return user;
    }
}
