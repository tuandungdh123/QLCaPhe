package com.example.qlcaphe.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticalDTO {
    public class DailyRevenue {
        private LocalDate orderDate;
        private Double totalRevenue;

        public DailyRevenue(LocalDate orderDate, Double totalRevenue) {
            this.orderDate = orderDate;
            this.totalRevenue = totalRevenue;
        }

        // getters và setters
    }

    public class MonthlyRevenue {
        private Integer year;
        private Integer month;
        private Double totalRevenue;

        public MonthlyRevenue(Integer year, Integer month, Double totalRevenue) {
            this.year = year;
            this.month = month;
            this.totalRevenue = totalRevenue;
        }

        // getters và setters
    }

    public class YearlyRevenue {
        private Integer year;
        private Double totalRevenue;

        public YearlyRevenue(Integer year, Double totalRevenue) {
            this.year = year;
            this.totalRevenue = totalRevenue;
        }

        // getters và setters
    }
}
