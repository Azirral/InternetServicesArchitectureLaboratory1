package org.model.order.function;

import org.model.order.dto.PutOrderRequest;
import org.model.order.entity.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

/**
 * Converts {@link PutOrderRequest} to {@link Order}. Caution, some fields are not set as they should be updated
 * by business logic.
 */
@Component
public class RequestToOrderFunction implements BiFunction<UUID, PutOrderRequest, Order> {

    @Override
    public Order apply(UUID orderId, PutOrderRequest request) {
        return Order.builder()
                .orderId(orderId)
                .orderDate(request.getOrderDate())
                .totalAmount(request.getTotalAmount())
                .build();
    }
}