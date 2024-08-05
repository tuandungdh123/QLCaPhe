package com.example.qlcaphe.Service;

import com.example.qlcaphe.Entity.CustomersEntity;
import com.example.qlcaphe.Entity.OrderE;
import com.example.qlcaphe.Entity.Order_Detail;
import com.example.qlcaphe.Entity.ShippingInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {

    @Transactional
    OrderE createOrder(int customerId, List<Order_Detail> orderDetails, ShippingInfo shippingInfo, Integer orderStatusId, Integer paymentMethodId);
}
