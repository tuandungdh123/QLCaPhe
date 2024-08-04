package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<CustomersEntity, Integer> {
}
