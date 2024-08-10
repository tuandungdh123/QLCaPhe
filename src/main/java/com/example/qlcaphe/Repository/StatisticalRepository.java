package com.example.qlcaphe.Repository;


import com.example.qlcaphe.DTO.StatisticalDTO;
import com.example.qlcaphe.Entity.OrderE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticalRepository extends JpaRepository<OrderE, Long> {
    @Query("SELECT DailyRevenue(CAST(o.orderDate AS date), SUM(o.totalAmount)) FROM OrderE o GROUP BY CAST(o.orderDate AS date) ORDER BY CAST(o.orderDate AS date)")
    List<StatisticalDTO.DailyRevenue> findDailyRevenue();

    @Query("SELECT MonthlyRevenue(YEAR(o.orderDate), MONTH(o.orderDate), SUM(o.totalAmount)) FROM OrderE o GROUP BY YEAR(o.orderDate), MONTH(o.orderDate) ORDER BY YEAR(o.orderDate), MONTH(o.orderDate)")
    List<StatisticalDTO.MonthlyRevenue> findMonthlyRevenue();

    @Query("SELECT YearlyRevenue(YEAR(o.orderDate), SUM(o.totalAmount)) FROM OrderE o GROUP BY YEAR(o.orderDate) ORDER BY YEAR(o.orderDate)")
    List<StatisticalDTO.YearlyRevenue> findYearlyRevenue();
}
