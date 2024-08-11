package com.example.qlcaphe.Api;

import com.example.qlcaphe.DTO.OrderRequest;
import com.example.qlcaphe.DTO.SearchDTO;
import com.example.qlcaphe.Entity.OrderE;
import com.example.qlcaphe.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oderApi")
public class OrderApi {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            // Gọi service để tạo đơn hàng
            OrderE order = orderService.createOrder(
                    request.getCustomerId(),
                    request.getOrderDetails(),
                    request.getShippingInfo(),
                    request.getOrderStatusId(), // Thêm ID trạng thái đơn hàng
                    request.getPaymentMethodId()
            );
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            // Xử lý lỗi khi dữ liệu không hợp lệ
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            // Xử lý lỗi khi lưu đơn hàng thất bại
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchDTO>> searchOrdersByPhone(@RequestParam("phone") String phone) {
        List<SearchDTO> searchResults = orderService.findOrdersByRecipientPhone(phone);

        if (searchResults != null && !searchResults.isEmpty()) {
            return ResponseEntity.ok(searchResults);
        } else {
            return ResponseEntity.noContent().build(); // Return 204 No Content if no results found
        }
    }
}
