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
@Table(name = "OrderStatus")
public class OrderStatus {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_Status_Id")
    private int orderStatusId;

    @Column(name = "Status_Name", nullable = false)
    private String statusName;

//    @OneToMany(mappedBy = "orderStatus", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonIgnoreProperties(value = {"OrderStatus", "hibernateLazyInitializer"})
//    private Set<OrderStatus> orderStatuses;
}
