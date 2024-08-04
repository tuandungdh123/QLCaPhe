package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Integer> {
    @Query(value = "SELECT Staff_Id, Name, Account, Password, Email, Phone, Address, Birthday, Status, isStaff FROM Staff WHERE Account = ?1 AND Password = ?2", nativeQuery = true)
    Optional<StaffEntity> getStaffByTkAndMk(String Account, String Password);
    Optional<StaffEntity> findById(Integer Staff_Id);
    StaffEntity findStaffByAccountAndPassword(String username, String password);
}
