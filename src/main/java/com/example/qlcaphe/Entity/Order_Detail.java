package com.example.qlcaphe.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Table(name = "Order_Details")
public class Order_Detail {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_Detail_Id")
    private int orderDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_Id", referencedColumnName = "Order_Id", nullable = false)
    @JsonIgnoreProperties(value = {"Order", "hibernateLazyInitializer"})
    private Order order;

    @Column(name = "Product_Id", nullable = false)
    private int productId;

    @Column(name = "Size_Id", nullable = false)
    private int sizeId;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    @Column(name = "Price", nullable = false)
    private double price;

    @Column(name = "Topping_Id")
    private Integer toppingId;
}
