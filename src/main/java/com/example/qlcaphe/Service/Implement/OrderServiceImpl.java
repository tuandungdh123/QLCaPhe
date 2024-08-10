package com.example.qlcaphe.Service.Implement;

import com.example.qlcaphe.Entity.*;
import com.example.qlcaphe.Repository.*;
import com.example.qlcaphe.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate; // Thêm JdbcTemplate



    @Override
    @Transactional
    public OrderE createOrder(int customerId, List<Order_Detail> orderDetails, ShippingInfo shippingInfo, Integer orderStatusId,Integer paymentMethodId) {
        if (paymentMethodId == null || paymentMethodId <= 0) {
            throw new IllegalArgumentException("Invalid payment method ID");
        }
        if (customerId <= 0) {
            throw new IllegalArgumentException("Invalid customer ID");
        }
        if (orderDetails == null || orderDetails.isEmpty()) {
            throw new IllegalArgumentException("Order details must not be null or empty");
        }
        if (shippingInfo == null) {
            throw new IllegalArgumentException("Shipping info must not be null");
        }
        if (orderStatusId <= 0) {
            throw new IllegalArgumentException("Invalid order status ID");
        }

        // Tìm khách hàng từ ID
        CustomersEntity existingCustomer = customersRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer with ID " + customerId + " does not exist"));

        // Tìm trạng thái đơn hàng theo ID
        OrderStatus orderStatus1 = orderStatusRepository.findById(orderStatusId)
                .orElseThrow(() -> new IllegalArgumentException("Trạng thái đơn hàng với ID " + orderStatusId + " không tồn tại"));

        // Tìm phương thức thanh toán từ ID
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new IllegalArgumentException("Payment method with ID " + paymentMethodId + " does not exist"));


        // Tạo đơn hàng mới
        OrderE order = new OrderE();
        order.setCustomer(existingCustomer);
        order.setOrderDate(new Date());
        order.setTotalAmount(orderDetails.stream().mapToDouble(Order_Detail::getPrice).sum());
        order.setOrderStatus(orderStatus1); // Thiết lập trạng thái đơn hàng

        // Thiết lập trạng thái đơn hàng


        OrderE savedOrder = orderRepository.save(order);

        if (savedOrder == null) {
            throw new RuntimeException("Failed to save order");
        }

        // Lưu chi tiết đơn hàng
        for (Order_Detail detail : orderDetails) {
            if (detail == null) {
                throw new IllegalArgumentException("Order detail must not be null");
            }
            detail.setOrder(savedOrder);
            orderDetailRepository.save(detail);
        }

        // Lưu thông tin vận chuyển
        shippingInfo.setPaymentMethod(paymentMethod);
        shippingInfo.setDeliveryTime(new Date());
        shippingInfo.setOrderE(savedOrder);
        shippingInfoRepository.save(shippingInfo);

        return savedOrder;
    }
}