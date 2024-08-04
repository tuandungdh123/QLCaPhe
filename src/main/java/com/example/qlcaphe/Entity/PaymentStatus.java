package com.example.qlcaphe.Entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Payment_Status")
public class PaymentStatus {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Payment_Status_Id")
    private int paymentStatusId;

    @Column(name = "Payment_Status_Name", nullable = false)
    private String paymentStatusName;
}
