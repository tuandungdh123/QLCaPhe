package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.OrderE;
import com.example.qlcaphe.Entity.Order_Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<Order_Detail, Integer> {
    List<Order_Detail> findByOrder(OrderE order);
}
