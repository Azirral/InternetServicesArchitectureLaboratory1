package org.model.order.function;

import org.model.order.dto.PatchOrderRequest;
import org.model.order.entity.Order;

import java.util.function.BiFunction;

/**
 * Returns a new instance of {@link Order} based on the provided value and updated with values from
 * {@link PatchOrderRequest}.
 */
public class UpdateOrderWithRequestFunction implements BiFunction<Order, PatchOrderRequest, Order> {

    @Override
    public Order apply(Order entity, PatchOrderRequest request) {
        return Order.builder()
                .orderId(entity.getOrderId())
                .orderDate(request.getOrderDate())
                .totalAmount(request.getTotalAmount())
                .customer(entity.getCustomer())
                .build();
    }
}