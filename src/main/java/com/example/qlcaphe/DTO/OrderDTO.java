package com.example.qlcaphe.DTO;

import com.example.qlcaphe.Entity.OrderStatus;
import com.example.qlcaphe.Entity.PaymentMethod;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
public class OrderDTO {
    private int customerId;
    private Date orderDate;
    private double totalAmount;
    private OrderStatus orderStatus;
    private List<OrderDetailDTO> orderDetails;
    private String recipientName;
    private String recipientPhone;
    private String address;
    private String addressNote;
    private Date deliveryTime;
    private PaymentMethod paymentMethod;
}
