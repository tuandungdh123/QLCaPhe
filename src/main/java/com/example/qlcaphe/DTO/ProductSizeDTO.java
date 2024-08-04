package com.example.qlcaphe.DTO;

import lombok.Data;

@Data
public class ProductSizeDTO {

    private Integer sizeId; // ID của kích thước
    private Double price;   // Giá của kích thước

    // Getters and Setters

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

