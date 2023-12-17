package org.model.order.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class GetOrdersResponse {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    @EqualsAndHashCode
    public static class Order {
        private UUID orderId;
        private String orderDate;
        private Double totalAmount;
    }
    @Singular
    private List<Order> orders;
}