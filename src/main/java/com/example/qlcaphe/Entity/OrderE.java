package com.example.qlcaphe.Entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Table(name = "Orders")
public class OrderE {

    @Id
    @Column(name = "Order_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oderId;

    @Column(name = "Customer_Id")
    private

}
