package org.model.order.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class GetOrderResponse {
    private UUID orderId;
    private String orderDate;
    private Double totalAmount;
    private UUID customerId;
}