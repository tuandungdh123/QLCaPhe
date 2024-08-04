package com.example.qlcaphe.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customers")
public class CustomersEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="Customer_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Phone_Number")
    private String phone;
}
