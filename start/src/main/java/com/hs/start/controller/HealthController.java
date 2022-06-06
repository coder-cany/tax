package com.hs.start.controller;

import com.hs.common.vo.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
    @PostMapping("/check")
    public Response check(){
        return Response.success();
    }


}
