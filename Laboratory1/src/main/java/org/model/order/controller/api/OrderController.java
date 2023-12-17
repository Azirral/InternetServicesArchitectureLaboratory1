package org.model.order.controller.api;
import org.model.order.dto.GetOrderResponse;
import org.model.order.dto.GetOrdersResponse;
import org.model.order.dto.PutOrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller for order resource. It does not return or receive entity objects but DTO objects which present only
 * those fields used in communication with the client.
 */
public interface OrderController {

    /**
     * @return list of orders
     */
    @GetMapping("api/orders")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetOrdersResponse getOrders();

    /**
     * @param customerId orders' customer
     * @return list of orders
     */
    @GetMapping("/api/customers/{customerId}/orders")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetOrdersResponse getCustomerOrders(
            @PathVariable("customerId")
            UUID customerId
    );

    /**
     * @param orderId order's id
     * @return single order
     */
    @GetMapping("/api/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetOrderResponse getOrder(
            @PathVariable("orderId")
            UUID orderId
    );

    /**
     * @param customerId customer's id
     * @param orderId    order's id
     * @return single order
     */
    @GetMapping("/api/customers/{customerId}/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetOrderResponse getCustomerOrder(
            @PathVariable("customerId")
            UUID customerId,
            @PathVariable("orderId")
            UUID orderId
    );

    /**
     * @param orderId      order's id
     * @param request new order
     */
    @PutMapping("/api/orders/{orderId}")
    @ResponseStatus(HttpStatus.CREATED)
    void putOrder(
            @PathVariable("orderId")
            UUID orderId,
            @RequestBody
            PutOrderRequest request
    );

    /**
     * Deletes selected order.
     *
     * @param orderId order's id
     */
    @DeleteMapping("/api/orders/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOrder(
            @PathVariable("orderId")
            UUID orderId
    );
}
