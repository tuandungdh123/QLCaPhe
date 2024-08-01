package com.example.qlcaphe.Api;

import com.example.qlcaphe.DTO.ProductDTO;
import com.example.qlcaphe.Entity.ProductE;
import com.example.qlcaphe.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/productApi")
public class ProductApi {
    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProduct")
    public ResponseEntity<?> doGetAllProduct(){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Get All Account Success");
            result.put("data", productService.getAllProductE());
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Get All Account Fail");
            result.put("data", null);
            log.error("Fail When Call API /java05/account-api/getAllAccount ", e);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/getSaveProduct")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            productService.saveProduct(productDTO);
            return ResponseEntity.ok("Product saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving product: " + e.getMessage());
        }
    }
}
