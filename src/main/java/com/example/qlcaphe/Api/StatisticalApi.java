package com.example.qlcaphe.Api;

import com.example.qlcaphe.Entity.OrderE;
import com.example.qlcaphe.Service.StatisticalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/statistical-api")
public class StatisticalApi {

    private static final Logger log = LoggerFactory.getLogger(StatisticalApi.class);
    @Autowired
    StatisticalService statisticalService;

    @GetMapping("/findByDay")
    public ResponseEntity<Map<String, Object>> findByDay(@RequestParam int day,
                                                         @RequestParam int month,
                                                         @RequestParam int year) {
        Map<String, Object> result = new HashMap<>();
        try {
            Float data = statisticalService.findByDay(day,month,year);
            if (data != null) {
                result.put("success", true);
                result.put("message", "Statistical Success");
                result.put("data", data);

            } else {
                result.put("success", false);
                result.put("message", "Statistical Fail");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Statistical Fail");
            result.put("data", null);
            log.error("Fail When Call API /accountApi/login ", e);
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/findByMonth")
    public ResponseEntity<Map<String, Object>> findByMonth(@RequestParam int month) {
        Map<String, Object> result = new HashMap<>();
        Map<Integer, Integer> data = statisticalService.findByMonth(month);
        try {
            if (data != null) {
                result.put("success", true);
                result.put("message", "Statistical Success");
                result.put("data", data);

            } else {
                result.put("success", false);
                result.put("message", "Statistical Fail");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Statistical Fail");
            result.put("data", null);
            log.error("Fail When Call API /accountApi/login ", e);
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/findByYear")
    public ResponseEntity<Map<String, Object>> findByYear(@RequestParam int year) {
        Map<String, Object> result = new HashMap<>();
        Map<Integer, Integer> data = statisticalService.findByYear(year);
        try {
            if (data != null) {
                result.put("success", true);
                result.put("message", "Statistical Success");
                result.put("data", data);

            } else {
                result.put("success", false);
                result.put("message", "Statistical Fail");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Statistical Fail");
            result.put("data", null);
            log.error("Fail When Call API /accountApi/login ", e);
        }
        return ResponseEntity.ok(result);
    }
}
