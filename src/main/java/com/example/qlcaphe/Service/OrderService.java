package com.example.qlcaphe.Service;

import com.example.qlcaphe.Entity.CustomersEntity;
import com.example.qlcaphe.Entity.OrderE;
import com.example.qlcaphe.Entity.Order_Detail;
import com.example.qlcaphe.Entity.ShippingInfo;

import java.util.List;

public interface OrderService {
    OrderE createOrder(CustomersEntity customer, List<Order_Detail> orderDetails, ShippingInfo shippingInfo);
}
