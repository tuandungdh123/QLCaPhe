package com.example.qlcaphe.Service.Implement;

import com.example.qlcaphe.Entity.CustomersEntity;
import com.example.qlcaphe.Entity.OrderE;
import com.example.qlcaphe.Entity.Order_Detail;
import com.example.qlcaphe.Entity.ShippingInfo;
import jakarta.transaction.Transactional;

import java.util.List;

public abstract class OrderService {
    @Transactional
    public abstract OrderE createOrder(CustomersEntity customer, List<Order_Detail> orderDetails, ShippingInfo shippingInfo);
}
