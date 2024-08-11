package com.example.qlcaphe.Service;

import java.util.Map;

public interface StatisticalService {
    Float findByDay(int day, int month, int year);

    Map<Integer, Integer> findByMonth(int month);

    Map<Integer, Integer> findByYear(int year);
}
