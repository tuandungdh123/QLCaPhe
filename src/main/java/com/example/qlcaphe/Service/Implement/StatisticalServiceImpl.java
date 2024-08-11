package com.example.qlcaphe.Service.Implement;


import com.example.qlcaphe.Entity.OrderE;
//import com.example.qlcaphe.Repository.StatisticalRepository;
import com.example.qlcaphe.Repository.StatisticalRepository;
import com.example.qlcaphe.Service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticalServiceImpl implements StatisticalService {
    @Autowired
    private StatisticalRepository statisticalRepository;

   public Float findByDay(int day, int month, int year){
       float statisticalDay = 0;
       List<OrderE> listOrder = statisticalRepository.findByDay(day, month, year);
       for(OrderE orderE : listOrder){
           statisticalDay += orderE.getTotalAmount();
       }
       return statisticalDay;
   }

    @Override
    public Map<Integer, Integer> findByMonth(int month) {
       Map<Integer, Integer> map = new HashMap<>();
       List<OrderE> orderEList = statisticalRepository.findByMonth(month);
       for(OrderE orderE : orderEList){
           int day = Integer.parseInt(orderE.getOrderDate().toString().split(" ")[0].split("-")[2]);
           if(map.containsKey(day)){
               map.replace(day, (int) (map.get(day) + orderE.getTotalAmount()));
           }else{
               map.put(day, (int) (orderE.getTotalAmount()));
           }
       }
       return map;
    }

    @Override
    public Map<Integer, Integer> findByYear(int year) {
//        float statisticalYear = 0;
        Map<Integer, Integer> map = new HashMap<>();
        List<OrderE> listOrder = statisticalRepository.findByYear(year);
        for(OrderE orderE : listOrder){
            int month = Integer.parseInt(orderE.getOrderDate().toString().split("-")[1]);
            if (map.containsKey(month)) {
                map.replace(month, (int) (map.get(month) + orderE.getTotalAmount()));
            } else {
                map.put(month, (int) (orderE.getTotalAmount()));
            }
        }
        return map;
    }

}
