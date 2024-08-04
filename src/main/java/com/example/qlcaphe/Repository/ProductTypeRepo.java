package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.ProductTypeE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductTypeRepo extends JpaRepository<ProductTypeE, Integer> {
    @Query(value = "SELECT Product_Type_Name, Product_Type_Id\n" +
            "FROM     Product_Type where Product_Type_Id = 1", nativeQuery = true)
    public List<ProductTypeE> findByProductTypeId(String Product_Type_Id);
}
