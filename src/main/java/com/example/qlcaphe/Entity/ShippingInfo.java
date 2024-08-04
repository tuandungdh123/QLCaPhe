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
@Table(name = "Shipping_Info")
public class ShippingInfo {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Shipping_Info_Id")
    private int shippingInfoId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_Id", referencedColumnName = "Order_Id", nullable = false)
    @JsonIgnoreProperties(value = {"OrderE", "hibernateLazyInitializer"})
    private OrderE orderE;

    @Column(name = "Recipient_Name", nullable = false)
    private String recipientName;

    @Column(name = "Recipient_Phone", nullable = false)
    private String recipientPhone;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "Address_Note")
    private String addressNote;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Delivery_Time", nullable = false)
    private Date deliveryTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Payment_Method_Id", referencedColumnName = "Payment_Method_Id", nullable = false)
    @JsonIgnoreProperties(value = {"shippingInfos", "hibernateLazyInitializer"})
    private PaymentMethod paymentMethod;
}
