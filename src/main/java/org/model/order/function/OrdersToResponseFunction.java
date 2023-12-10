package org.model.order.function;
import org.model.order.dto.GetOrdersResponse;
import org.model.order.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

/**
 * Converts {@link List<Order>} to {@link GetOrdersResponse}.
 */
@Component
public class OrdersToResponseFunction implements Function<List<Order>, GetOrdersResponse> {

    @Override
    public GetOrdersResponse apply(List<Order> entities) {
        return GetOrdersResponse.builder()
                .orders(entities.stream()
                        .map(order -> GetOrdersResponse.Order.builder()
                                .orderId(order.getOrderId())
                                .orderDate(order.getOrderDate())
                                .totalAmount(order.getTotalAmount())
                                .build())
                        .toList())
                .build();
    }
}
