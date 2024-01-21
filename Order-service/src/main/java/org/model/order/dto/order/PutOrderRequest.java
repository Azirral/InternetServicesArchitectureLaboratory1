package org.model.order.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;
@Data
@Builder
public class PutOrderRequest {
    private String date;
    private Double amount;
    @JsonProperty("customer_id")
    private UUID customerId;
}
