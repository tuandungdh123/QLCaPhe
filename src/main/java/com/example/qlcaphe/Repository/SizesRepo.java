package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.SizeE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizesRepo extends JpaRepository<SizeE, Integer> {
}
