package com.hs.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ResourceMVCConfig implements WebMvcConfigurer {

    //使得更新的图片可以立即显示
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取文件的真实路径
        String path = System.getProperty("user.dir") + "\\order\\src\\main\\resources\\static\\picture\\user\\avatar\\";
//        String path = "/usericon/";
        //       /images/**是对应resource下工程目录
        registry.addResourceHandler("/user/avatar/**").addResourceLocations("file:" + path);

        //获取文件的真实路径
//        String articleImgPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\articleimg\\";
//        String articleImgPath = "/articleimg/";
        //       /images/**是对应resource下工程目录
//        registry.addResourceHandler("/articleimg/**").addResourceLocations("file:"+articleImgPath);
    }
}
