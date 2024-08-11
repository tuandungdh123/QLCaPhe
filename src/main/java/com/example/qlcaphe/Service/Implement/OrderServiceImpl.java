package com.example.qlcaphe.Service.Implement;

import com.example.qlcaphe.DTO.SearchDTO;
import com.example.qlcaphe.Entity.*;
import com.example.qlcaphe.Repository.*;
import com.example.qlcaphe.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductRepo productRepo;

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
    public OrderE createOrder(int customerId, List<Order_Detail> orderDetails, ShippingInfo shippingInfo, Integer orderStatusId, Integer paymentMethodId) {
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
                .orElseThrow(() -> new IllegalArgumentException("Order status with ID " + orderStatusId + " does not exist"));

        // Tìm phương thức thanh toán từ ID
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new IllegalArgumentException("Payment method with ID " + paymentMethodId + " does not exist"));

        // Tạo đơn hàng mới
        OrderE order = new OrderE();
        order.setCustomer(existingCustomer);
        order.setOrderDate(new Date());
        order.setTotalAmount(orderDetails.stream().mapToDouble(detail -> detail.getPrice() * detail.getQuantity()).sum());
        order.setOrderStatus(orderStatus1);

        // Lưu đơn hàng
        OrderE savedOrder = orderRepository.save(order);

        // Logging thông tin đơn hàng đã lưu
        System.out.println("Order saved: " + savedOrder);

        if (savedOrder == null) {
            throw new RuntimeException("Failed to save order");
        }

        // Lưu chi tiết đơn hàng và gán chúng vào đơn hàng mới tạo
        for (Order_Detail detail : orderDetails) {
            if (detail == null) {
                throw new IllegalArgumentException("Order detail must not be null");
            }
            // Gán đối tượng OrderE vào chi tiết đơn hàng
            detail.setOrder(savedOrder);
            // Logging chi tiết đơn hàng trước khi lưu
            System.out.println("Saving Order Detail: " + detail);
            // Lưu chi tiết đơn hàng
            orderDetailRepository.save(detail);
        }

        // Lưu thông tin vận chuyển
        shippingInfo.setPaymentMethod(paymentMethod);
        shippingInfo.setDeliveryTime(new Date());
        shippingInfo.setOrderE(savedOrder); // Gán đơn hàng vào thông tin vận chuyển
        shippingInfoRepository.save(shippingInfo);

        // Logging thông tin vận chuyển đã lưu
        System.out.println("Shipping Info saved: " + shippingInfo);

        return savedOrder;
    }


    @Override
    public List<SearchDTO> findOrdersByRecipientPhone(String recipientPhone) {
        List<ShippingInfo> shippingInfos = shippingInfoRepository.findByRecipientPhone(recipientPhone);

        return shippingInfos.stream()
                .map(shippingInfo -> {
                    OrderE order = shippingInfo.getOrderE();
                    List<Order_Detail> orderDetails = orderDetailRepository.findByOrder(order);

                    // Get payment method
                    String paymentMethod = shippingInfo.getPaymentMethod() != null ?
                            shippingInfo.getPaymentMethod().getPaymentMethodName() : "N/A";

                    // Get order status
                    String orderStatus = order.getOrderStatus() != null ?
                            order.getOrderStatus().getStatusName() : "N/A";

                    // Combine data into DTO
                    SearchDTO orderDTO = new SearchDTO();
                    orderDTO.setOrderId(order.getOrderId());
                    orderDTO.setTotalAmount(order.getTotalAmount());
                    orderDTO.setPaymentMethod(paymentMethod);
                    orderDTO.setOrderStatus(orderStatus);
                    orderDTO.setRecipientPhone(shippingInfo.getRecipientPhone());
                    orderDTO.setRecipientName(shippingInfo.getRecipientName());
                    orderDTO.setAddress(shippingInfo.getAddress());


                    // Get the product name from the order details
                    if (!orderDetails.isEmpty()) {
                        Order_Detail orderDetail = orderDetails.get(0); // Assuming there's at least one detail
                        ProductE product = productRepo.findById(orderDetail.getProductId()).orElse(null);
                        if (product != null) {
                            orderDTO.setProductName(product.getNameProduct());
                        }
                    }

                    return orderDTO;
                })
                .collect(Collectors.toList());
    }
}