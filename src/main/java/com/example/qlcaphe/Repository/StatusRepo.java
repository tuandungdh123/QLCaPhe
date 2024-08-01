package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepo extends JpaRepository<StatusEntity, Integer> {
    @Query(value = "SELECT Status_Id, Status_Name FROM StatusEntity WHERE Status = ?", nativeQuery = true)
    Optional<StatusEntity> findByStatus_Name(String Status);
}
