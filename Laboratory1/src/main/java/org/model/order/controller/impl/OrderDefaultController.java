package org.model.order.controller.impl;
import lombok.extern.java.Log;
import org.model.order.controller.api.OrderController;
import org.model.order.entity.Order;
import org.model.order.dto.GetOrderResponse;
import org.model.order.dto.GetOrdersResponse;
import org.model.order.dto.PutOrderRequest;
import org.model.order.function.OrderToResponseFunction;
import org.model.order.function.OrdersToResponseFunction;
import org.model.order.function.RequestToOrderFunction;
import org.model.order.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * Controller for order resource. It does not return or receive entity objects but DTO objects which present only
 * those fields used in communication with the client.
 */
@RestController
@Log
public class OrderDefaultController implements OrderController {

    /**
     * Service for managing orders.
     */
    private final OrderService orderService;

    /**
     * Converts {@link Order} to {@link GetOrderResponse}.
     */
    private final OrderToResponseFunction orderToResponse;

    /**
     * Converts {@link List<Order>} to {@link GetOrdersResponse}.
     */
    private final OrdersToResponseFunction ordersToResponse;

    /**
     * Converts {@link PutOrderRequest} to {@link Order}.
     */
    private final RequestToOrderFunction requestToOrder;

    /**
     * @param orderService       service for managing orders
     * @param orderToResponse    converts {@link Order} to {@link GetOrderResponse}
     * @param ordersToResponse   coverts {@link List<Order>} to {@link GetOrdersResponse}
     * @param requestToOrder     converts {@link PutOrderRequest} to {@link Order}
     */
    @Autowired
    public OrderDefaultController(
            OrderService orderService,
            OrderToResponseFunction orderToResponse,
            OrdersToResponseFunction ordersToResponse,
            RequestToOrderFunction requestToOrder
    ) {
        this.orderService = orderService;
        this.orderToResponse = orderToResponse;
        this.ordersToResponse = ordersToResponse;
        this.requestToOrder = requestToOrder;
    }

    @Override
    public GetOrdersResponse getOrders() {
        return ordersToResponse.apply(orderService.findAll());
    }

    @Override
    public GetOrdersResponse getCustomerOrders(UUID customerId) {
        return orderService.findAllByCustomerId(customerId)
                .map(ordersToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetOrderResponse getOrder(UUID orderId) {
        return orderService.find(orderId)
                .map(orderToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetOrderResponse getCustomerOrder(UUID customerId, UUID orderId) {
        return null;
    }

    @Override
    public void putOrder(UUID orderId, PutOrderRequest request) {
        orderService.create(requestToOrder.apply(orderId, request));
    }

    @Override
    public void deleteOrder(UUID orderId) {
        orderService.find(orderId)
                .ifPresentOrElse(
                        order -> orderService.delete(orderId),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }
}
