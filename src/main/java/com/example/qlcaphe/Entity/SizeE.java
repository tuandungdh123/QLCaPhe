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
@Table(name = "Sizes")
public class SizeE {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Size_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sizeId;

    @Column(name = "Size_Name")
    private String sizeName;

    @OneToMany(mappedBy = "sizeE")
    @JsonIgnoreProperties(value = {"sizeE", "hibernateLazyInitializer"})
    private List<Product_SizeE> productSizes;
}
