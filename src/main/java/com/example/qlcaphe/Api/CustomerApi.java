package com.example.qlcaphe.Api;


import com.example.qlcaphe.Entity.CustomersEntity;
import com.example.qlcaphe.Service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/customer-api")
public class CustomerApi {
    @Autowired
    private CustomerService customers;

    @GetMapping("/getAllCustomer")
    public ResponseEntity<?> doGetAllCustomer() {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Get All Account Success");
            result.put("data", customers.getAllCustomers());
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Get All Account Fail");
            result.put("data", null);
            log.error("Fail When Call API getAllCustomer ", e);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> doPostLogin(@RequestBody CustomersEntity customersEntity) {
        Map<String, Object> result = new HashMap<>();
        try {
            var data = customers.getCustomersByTkAndMk(customersEntity.getUsername(), customersEntity.getPassword());
            if (!data.isEmpty()) {
                result.put("success", true);
                result.put("message", "Login Success");
                result.put("data", data);
            } else {
                result.put("success", false);
                result.put("message", "Login Fail");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Login Fail");
            result.put("data", null);
            log.error("Fail When Call API /accountApi/login ", e);
        }
        return ResponseEntity.ok(result);
    }
}
