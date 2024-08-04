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
@Table(name = "Product_Sizes")
public class Product_SizeE {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Product_Size_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productSizeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Product_Id", referencedColumnName = "Product_Id")
    @JsonIgnoreProperties(value = {"productSizes", "hibernateLazyInitializer"})
    private ProductE productE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Size_Id", referencedColumnName = "Size_Id")
    @JsonIgnoreProperties(value = {"productSizes", "hibernateLazyInitializer"})
    private SizeE sizeE;

    @Column(name = "Price")
    private Double price;
}
