package com.example.qlcaphe.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Payment_Method")
public class PaymentMethod {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Payment_Method_Id")
    private int paymentMethodId;

    @Column(name = "Payment_Method_Name", nullable = false)
    private String paymentMethodName;

    @OneToMany(mappedBy = "paymentMethod", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"ShippingInfo", "hibernateLazyInitializer"})
    private Set<ShippingInfo> shippingInfos;
}
