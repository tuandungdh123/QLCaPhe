package com.example.qlcaphe.Service;

import com.example.qlcaphe.DTO.ProductDTO;
import com.example.qlcaphe.Entity.ProductE;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ProductService{

    @Transactional
    void saveProduct(ProductDTO productDTO);

    List<ProductE> getAllProductE();
}
