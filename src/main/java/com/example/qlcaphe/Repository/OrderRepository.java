package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.OrderE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderE, Integer> {
}
