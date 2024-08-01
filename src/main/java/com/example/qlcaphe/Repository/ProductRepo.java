package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.ProductE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductE, Integer> {
}
