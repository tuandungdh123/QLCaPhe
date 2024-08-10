package com.example.qlcaphe.Service;

import com.example.qlcaphe.DTO.StatisticalDTO;

import java.util.List;

public interface StatisticalService {
    List<StatisticalDTO.DailyRevenue> getDailyRevenue();
    List<StatisticalDTO.MonthlyRevenue> getMonthlyRevenue();
    List<StatisticalDTO.YearlyRevenue> getYearlyRevenue();
}
