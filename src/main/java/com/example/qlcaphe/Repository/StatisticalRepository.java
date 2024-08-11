package com.example.qlcaphe.Repository;

import com.example.qlcaphe.Entity.OrderE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticalRepository extends JpaRepository<OrderE, Integer> {

    @Query(value = "SELECT * FROM Orders WHERE Day(Order_Date) = ?1 AND MONTH(Order_Date) = ?2 AND YEAR(Order_Date) = ?3", nativeQuery = true)
    List<OrderE> findByDay(int day, int month, int year);

    @Query(value = "SELECT * FROM Orders WHERE MONTH(Order_Date) = ?1 AND YEAR(Order_Date) = ?2", nativeQuery = true)
    List<OrderE> findByMonth(int month, int year);

    @Query(value = "SELECT * FROM Orders WHERE YEAR(Order_Date) = ?1", nativeQuery = true)
    List<OrderE> findByYear(int year);

}
