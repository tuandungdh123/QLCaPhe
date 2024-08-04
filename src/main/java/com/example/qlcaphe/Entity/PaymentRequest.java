package com.example.qlcaphe.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    @JsonProperty("amount")
    private String amount;

    @JsonProperty("orderInfo")
    private String orderInfo;
//
//    // Getter và Setter
//    public String getAmount() {
//        return amount;
//    }
//
//    public void setAmount(String amount) {
//        this.amount = amount;
//    }
//
//    public String getOrderInfo() {
//        return orderInfo;
//    }
//
//    public void setOrderInfo(String orderInfo) {
//        this.orderInfo = orderInfo;
//    }
}
