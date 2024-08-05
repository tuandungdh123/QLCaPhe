package com.example.qlcaphe.Api;

import com.example.qlcaphe.Entity.CustomersEntity;
import com.example.qlcaphe.Service.registerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@Controller
@RequestMapping("/register-api")
public class registerApi {
    @Autowired
    private registerService regServ;
    @PostMapping ("/register")
    public ResponseEntity<?> doPostRegister(@RequestBody CustomersEntity customersEntity){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Get All Account Success");
            result.put("data", regServ.doSaveCustomer(customersEntity));
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Get All Account Fail");
            result.put("data", null);
            log.error("Fail When Call API /java05/account-api/getAllAccount ", e);
        }
        return ResponseEntity.ok(result);
    }
}
