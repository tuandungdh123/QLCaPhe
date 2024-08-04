package com.example.qlcaphe.DTO;

import com.example.qlcaphe.Entity.ProductE;
import com.example.qlcaphe.Entity.SizeE;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class OrderDetailDTO {
    private ProductE productE;
    private SizeE sizeE;
    private int quantity;
    private double price;
}
