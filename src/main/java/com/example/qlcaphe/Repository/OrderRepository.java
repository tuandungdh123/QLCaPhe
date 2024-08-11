package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.OrderE;
import com.example.qlcaphe.Entity.OrderStatus;
import com.example.qlcaphe.Entity.ShippingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderE, Integer> {
    // Tìm tất cả các thông tin vận chuyển theo số điện thoại
}
