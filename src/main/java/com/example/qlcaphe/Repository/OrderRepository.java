package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.OrderE;
import com.example.qlcaphe.Entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderE, Integer> {
}
