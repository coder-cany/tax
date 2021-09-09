package com.hs.start.service;

import com.hs.common.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "order",url = "http://localhost:2001")
public interface OrderService {
    @PostMapping("/order")
    void order(Order order);
}
