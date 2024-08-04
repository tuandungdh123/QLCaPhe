package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.Product_SizeE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepo extends JpaRepository<Product_SizeE, Integer> {
}
