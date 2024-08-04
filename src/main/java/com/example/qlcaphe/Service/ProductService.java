package com.example.qlcaphe.Service;

import com.example.qlcaphe.DTO.ProductDTO;
import com.example.qlcaphe.Entity.ProductE;
import com.example.qlcaphe.Entity.Product_SizeE;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductService{

    @Transactional
    void saveProduct(ProductDTO productDTO);

    List<ProductE> getAllProductE();

    Optional<ProductE> getTourEByTourId(Integer productId);

    Optional<Product_SizeE> getPriceByProductIdAndSizeId(Integer productId, Integer sizeId);
}
