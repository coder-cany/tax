package com.hs.order.controller;

import com.hs.order.service.BusinessService;
import com.hs.common.vo.Response;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BusinessController {
    private BusinessService taskService;

    @PostMapping("/task")
    public void tasks(@RequestParam("token") String token,
                      @RequestParam("nextPerson") String nextPerson,
                      @RequestParam("orderId") String orderId,
                      @RequestParam("service") String service
    ) {
        taskService.createBusiness(token, nextPerson, orderId, service);
    }

    @GetMapping("/company/tasks")
    public Response tasks(@RequestParam("token") String token) {
        return new Response<List>(200, "ok", taskService.getBusiness(token));
    }
}
