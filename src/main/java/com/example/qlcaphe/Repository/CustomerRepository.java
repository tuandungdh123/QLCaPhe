package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomersEntity,Integer> {
    @Query(value = "SELECT Customer_Id, Username, Password, Phone_Number FROM Customers WHERE Username = ?1", nativeQuery = true)
    Optional<CustomersEntity> getCustomersByTk(String username);
}
