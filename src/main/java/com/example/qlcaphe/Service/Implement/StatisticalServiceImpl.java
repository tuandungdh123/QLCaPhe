package com.example.qlcaphe.Service.Implement;


import com.example.qlcaphe.DTO.StatisticalDTO;
import com.example.qlcaphe.Repository.OrderRepository;
import com.example.qlcaphe.Repository.StatisticalRepository;
import com.example.qlcaphe.Service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticalServiceImpl implements StatisticalService {
    @Autowired
    private StatisticalRepository statisticalRepository;

    public List<StatisticalDTO.DailyRevenue> getDailyRevenue() {
        return statisticalRepository.findDailyRevenue();
    }

    public List<StatisticalDTO.MonthlyRevenue> getMonthlyRevenue() {
        return statisticalRepository.findMonthlyRevenue();
    }

    public List<StatisticalDTO.YearlyRevenue> getYearlyRevenue() {
        return statisticalRepository.findYearlyRevenue();
    }

}
