package com.example.qlcaphe.DTO;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {
    private int orderId;
    private String productName;
    private double totalAmount;
    private String paymentMethod;
    private String orderStatus;
    private String recipientPhone;
    private String recipientName;
    private String address;
    private int quantity;
    private String sizeName;
}
