package com.example.qlcaphe.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;



@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product_Type")
public class ProductTypeE {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Product_Type_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productTypeId;

    @Column(name = "Product_Type_Name")
    private String productTypeName;
}
