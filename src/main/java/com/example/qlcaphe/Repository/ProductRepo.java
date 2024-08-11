package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.ProductE;
import com.example.qlcaphe.Entity.Product_SizeE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductE, Integer> {

    ProductE getTourEByProductId(Integer productId);

    @Query("SELECT ps, p.nameProduct FROM Product_SizeE ps JOIN ps.productE p WHERE ps.productE.productId = :productId AND ps.sizeE.sizeId = :sizeId")
    Optional<Product_SizeE> findPriceByProductIdAndSizeId(@Param("productId") Integer productId, @Param("sizeId") Integer sizeId);


//    Optional<ProductE> findById(Integer productId);
}
