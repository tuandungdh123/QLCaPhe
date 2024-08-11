package com.example.qlcaphe.Api;

import com.example.qlcaphe.Entity.CustomersEntity;
import com.example.qlcaphe.Entity.StaffEntity;
import com.example.qlcaphe.Service.AuthenticateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/authen-api")
public class AuthenApi {
    @Autowired
    private AuthenticateService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginData) throws SQLException {
        String username = loginData.get("username");
        String password = loginData.get("password");

        Object user = authService.authenticateUser(username);

        Map<String, Object> response = new HashMap<>();
        if (user != null) {
            response.put("success", true);
            if (user instanceof StaffEntity) {

                StaffEntity staff = (StaffEntity) user;
                response.put("userType", "admin");
                response.put("user", user);
                response.put("userId", staff.getStaff_Id()); // Assuming getStaffId() is available
                response.put("phone", staff.getPhone());
            } else {
                CustomersEntity customer = (CustomersEntity) user;
                response.put("userType", "customer");
                response.put("user", user);
                response.put("userId", customer.getCustomerId()); // Assuming getCustomerId() is available
                response.put("phone", customer.getPhone());
            }
        } else {
            response.put("success", false);
            response.put("message", "Invalid username or password");
        }

        return ResponseEntity.ok(response);
    }
}
