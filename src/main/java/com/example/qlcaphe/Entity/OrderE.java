package com.example.qlcaphe.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Orders")
public class OrderE {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_Id")
    private int orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Customer_Id", referencedColumnName = "Customer_Id", nullable = false)
    @JsonIgnoreProperties(value = {"orders", "hibernateLazyInitializer"})
    private CustomersEntity customer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Order_Date", nullable = false)
    private Date orderDate;

    @Column(name = "Total_Amount", nullable = false)
    private double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_Status_Id", referencedColumnName = "Order_Status_Id", nullable = false)
    @JsonIgnoreProperties(value = {"orders", "hibernateLazyInitializer"})
    private OrderStatus orderStatus;

}
