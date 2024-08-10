package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.CustomerDetailE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDetailRepo extends JpaRepository<CustomerDetailE, Integer>{
    // Tìm thông tin chi tiết theo customerId
    Optional<CustomerDetailE> findByCustomerCustomerId(int customerId);
}
