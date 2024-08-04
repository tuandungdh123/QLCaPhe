package com.example.qlcaphe.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class ProductE {
        private static final long serialVersionUID = 1L;

        @Id
        @Column(name="Product_Id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int productId;

        @Column(name = "Name_Product")
        private String nameProduct;

        @Column(name = "Description")
        private String description;

        @Column(name = "Product_Image")
        private String priductImage;

        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "Product_Type_Id", referencedColumnName = "Product_Type_Id")
        @JsonIgnoreProperties(value = {"ProductTypeE" ,"hibernateLazyInitializer"})
        private ProductTypeE productTypeE;

        @OneToMany(mappedBy = "productE", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonIgnoreProperties(value = {"productE", "hibernateLazyInitializer"})
        private List<Product_SizeE> productSizes;
}
