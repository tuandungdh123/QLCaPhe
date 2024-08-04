package com.example.qlcaphe.Service.Implement;

import com.example.qlcaphe.Entity.*;
import com.example.qlcaphe.Repository.*;
import com.example.qlcaphe.Service.OrderService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerRepository customersRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ShippingInfoRepository shippingInfoRepository;

    @Override
    @Transactional
    public OrderE createOrder(CustomersEntity customer, List<Order_Detail> orderDetails, ShippingInfo shippingInfo) {
        // Lưu thông tin khách hàng
        CustomersEntity savedCustomer = customersRepository.save(customer);

        // Tạo đơn hàng mới
        OrderE order = new OrderE();
        order.setCustomer(savedCustomer);
        order.setOrderDate(new Date());
        order.setTotalAmount(orderDetails.stream().mapToDouble(Order_Detail::getPrice).sum());
        OrderE savedOrder = orderRepository.save(order);

        // Lưu chi tiết đơn hàng
        for (Order_Detail detail : orderDetails) {
            detail.setOrder(savedOrder);
            orderDetailRepository.save(detail);
        }

        // Lưu thông tin vận chuyển
        shippingInfo.setOrderE(savedOrder);
        shippingInfoRepository.save(shippingInfo);

        return savedOrder;
    }
}