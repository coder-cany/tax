package com.hs.start.controller;

import com.hs.common.entity.Order;
import com.hs.common.vo.Response;
import com.hs.start.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/order")
    public Response order(@RequestBody Order order){
        orderService.order(order);
        return Response.success();
    }
}
