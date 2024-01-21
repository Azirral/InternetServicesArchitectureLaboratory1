package org.model.order.service.order.impl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.model.order.dto.order.GetOrderDetailsResponse;
import org.model.order.dto.order.GetOrdersResponse;
import org.model.order.dto.order.PutOrderRequest;
import org.model.order.entity.Customer;
import org.model.order.exception.CustomerNotFoundException;
import org.model.order.exception.OrderNotFoundException;
import org.model.order.mapper.OrderMapper;
import org.model.order.repository.CustomerRepository;
import org.model.order.entity.Order;
import org.model.order.repository.OrderRepository;
import org.model.order.service.order.api.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderDefaultService implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void addOrder(PutOrderRequest putOrderRequest) {
        Customer customer = customerRepository.findById(putOrderRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + putOrderRequest.getCustomerId()));
        Order order =
                OrderMapper.mapToOrder(putOrderRequest, customer, UUID.randomUUID());
        orderRepository.save(order);
        
    }

    @Override
    public GetOrdersResponse getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return OrderMapper.mapToGetOrdersResponse(orders);
    }

    @Override
    public void deleteOrder(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));
        orderRepository.delete(order);
        
    }

    @Override
    public GetOrderDetailsResponse updateOrder(UUID id, PutOrderRequest putOrderRequest) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));
        existingOrder.setCustomer(customerRepository.findById(putOrderRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + id)));

        existingOrder.setDate(putOrderRequest.getDate());
        existingOrder.setAmount(putOrderRequest.getAmount());

        orderRepository.save(existingOrder);

        return OrderMapper.mapToGetOrderDetailsResponse(existingOrder);
    }

    @Override
    public GetOrderDetailsResponse getOrderById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));
        return OrderMapper.mapToGetOrderDetailsResponse(order);
    }

    @Override
    public GetOrdersResponse getOrderByCustomer(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + id));


        return OrderMapper.mapToGetOrdersResponse(customer.getOrders());
    }
}
