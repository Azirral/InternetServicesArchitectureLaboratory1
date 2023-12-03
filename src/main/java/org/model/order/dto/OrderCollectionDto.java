package org.model.order.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCollectionDto {
    private UUID orderId;
    private String orderDate;
    private Double totalAmount;
    private UUID customerId;
}