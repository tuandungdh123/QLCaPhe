package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.ShippingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Integer> {
    List<ShippingInfo> findByRecipientPhone(String recipientPhone);
}
