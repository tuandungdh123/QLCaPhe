package com.example.qlcaphe.Api;

import com.example.qlcaphe.DTO.ProductDTO;
import com.example.qlcaphe.Entity.ProductE;
import com.example.qlcaphe.Entity.Product_SizeE;
import com.example.qlcaphe.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/getProductByProductId")
    public ResponseEntity<?> doGetProductByProductId(@RequestParam("productId") Integer productId){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Get All Account Success");
            result.put("data", productService.getTourEByTourId(productId));
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Get All Account Fail");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getProductById")
    public ResponseEntity<?> getProductById(@RequestParam("productId") Integer productId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Optional<ProductE> productOptional = productService.getTourEByTourId(productId);
            if (productOptional.isPresent()) {
                result.put("status", true);
                result.put("message", "Product found");
                result.put("data", productOptional.get());
            } else {
                result.put("status", false);
                result.put("message", "Product not found");
                result.put("data", null);
            }
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Get Product Failed");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getPriceByProductIdAndSizeId")
    public ResponseEntity<?> getPriceByProductIdAndSizeId(@RequestParam("productId") Integer productId, @RequestParam("sizeId") Integer sizeId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Optional<Product_SizeE> productSize = productService.getPriceByProductIdAndSizeId(productId, sizeId);
            if (productSize.isPresent()) {
                result.put("status", true);
                result.put("message", "Price retrieved successfully");
                result.put("data", productSize.get());
            } else {
                result.put("status", false);
                result.put("message", "Price not found");
                result.put("data", null);
            }
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Error retrieving price");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping("/getSaveProduct")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            productService.saveProduct(productDTO);
            return ResponseEntity.ok("Product saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error saving product: " + e.getMessage());
        }
    }
}
