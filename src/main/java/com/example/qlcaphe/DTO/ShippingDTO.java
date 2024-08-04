package com.example.qlcaphe.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class ShippingDTO {
    private String recipientName;
    private String recipientPhone;
    private String address;
    private String addressNote;
    private Date deliveryTime;
    private int paymentMethodId;
}
