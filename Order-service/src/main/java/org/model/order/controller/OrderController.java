package org.model.order.controller;
import lombok.RequiredArgsConstructor;
import org.model.order.dto.order.GetOrderDetailsResponse;
import org.model.order.dto.order.GetOrdersResponse;
import org.model.order.dto.order.PutOrderRequest;
import org.model.order.service.order.api.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller for order resource. It does not return or receive entity objects but DTO objects which present only
 * those fields used in communication with the client.
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping()
    public ResponseEntity<Void> addNewOrder(@RequestBody PutOrderRequest putOrderRequest) {
        System.out.println(putOrderRequest);
        orderService.addOrder(putOrderRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOrderDetailsResponse> getOrderById(@PathVariable UUID id){
    GetOrderDetailsResponse order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetOrderDetailsResponse> updateOrder(
            @PathVariable UUID id,
            @RequestBody PutOrderRequest putOrderRequest) {
            GetOrderDetailsResponse updatedOrder = orderService.updateOrder(id, putOrderRequest);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
            orderService.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<GetOrdersResponse> getOrders(@RequestParam(name = "customer_id", required = false) UUID customerId) {
        GetOrdersResponse orders;
        if (customerId != null) {
            orders = orderService.getOrderByCustomer(customerId);
        } else {
            orders = orderService.getAllOrders();
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
