package com.example.qlcaphe.Api;

import com.example.qlcaphe.Entity.PaymentRequest;
import com.example.qlcaphe.Service.Implement.MomoPaymentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MomoApi {

    @Autowired
    private MomoPaymentImpl momoPaymentService;

    @PostMapping("/momo")
    public ResponseEntity<String> createMomoPayment(@RequestBody Map<String, String> request) {
        try {
            String orderInfo = request.get("orderInfo");
            String amount = request.get("amount");
            String payUrl = momoPaymentService.createPaymentRequest(orderInfo, amount);
            return ResponseEntity.ok(payUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment request failed: " + e.getMessage());
        }
    }

}