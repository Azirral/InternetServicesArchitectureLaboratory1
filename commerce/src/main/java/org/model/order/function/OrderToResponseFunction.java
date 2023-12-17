package org.model.order.function;
import org.model.order.dto.GetOrderResponse;
import org.model.order.entity.Order;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Converts {@link Order} to {@link GetOrderResponse}.
 */
@Component
public class OrderToResponseFunction implements Function<Order, GetOrderResponse> {

    @Override
    public GetOrderResponse apply(Order entity) {
        return GetOrderResponse.builder()
                .orderId(entity.getOrderId())
                .orderDate(entity.getOrderDate())
                .totalAmount(entity.getTotalAmount())
                .customerId(entity.getCustomer().getCustomerId()) // Assuming Customer has getId() method
                .build();
    }
}