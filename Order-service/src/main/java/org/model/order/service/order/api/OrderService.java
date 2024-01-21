package org.model.order.service.order.api;
import org.model.order.dto.order.GetOrderDetailsResponse;
import org.model.order.dto.order.GetOrdersResponse;
import org.model.order.dto.order.PutOrderRequest;
import org.model.order.entity.Order;

import java.util.UUID;

/**
 * Service layer for all business actions regarding order entity.
 */
public interface OrderService {
    void addOrder(PutOrderRequest putOrderRequest);
    GetOrdersResponse getAllOrders();
    void deleteOrder(UUID id);
    GetOrderDetailsResponse updateOrder(UUID id, PutOrderRequest putOrderRequest);
    GetOrderDetailsResponse getOrderById(UUID id);
    GetOrdersResponse getOrderByCustomer(UUID id);
}