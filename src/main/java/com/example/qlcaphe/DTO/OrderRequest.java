package com.example.qlcaphe.DTO;

import com.example.qlcaphe.Entity.CustomersEntity;
import com.example.qlcaphe.Entity.Order_Detail;
import com.example.qlcaphe.Entity.ShippingInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class OrderRequest {
    private CustomersEntity customer;
    private List<Order_Detail> orderDetails;
    private ShippingInfo shippingInfo;

}
