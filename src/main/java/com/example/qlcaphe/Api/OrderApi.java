package com.example.qlcaphe.Api;

import com.example.qlcaphe.DTO.OrderRequest;
import com.example.qlcaphe.Entity.OrderE;
import com.example.qlcaphe.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oderApi")
public class OrderApi {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public OrderE createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest.getCustomer(), orderRequest.getOrderDetails(), orderRequest.getShippingInfo());
    }

}
