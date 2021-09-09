package com.hs.order.controller;

import com.hs.common.entity.Order;
import com.hs.order.exception.SimpleException;
import com.hs.order.service.OrderService;
import com.hs.common.vo.Response;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/order")
    public Response order(@RequestBody Order order,
                          @RequestParam(value = "material", required = false) MultipartFile material
    ) {
        try {
            orderService.order(order, material);
            return Response.success(order);
        } catch (SimpleException e) {
            return Response.fail(e.getMessage());
        }
    }

    @GetMapping("/company/orders")
    public Response orders(@RequestParam("token") String token) {
        return new Response<List>(200, "ok", orderService.getOrders(token));
    }
}
