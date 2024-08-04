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
}
