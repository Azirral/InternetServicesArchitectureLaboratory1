package org.model.order.service;

import jakarta.persistence.EntityNotFoundException;
import org.model.customer.entity.Customer;
import org.model.customer.repository.CustomerRepository;
import org.model.order.entity.Order;
import org.model.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    // Existing functions
    @Transactional
    public List<Order> listElements() {
        List<Order> elements = orderRepository.findAll();
        if (elements.isEmpty()) {
            System.out.println("No elements found.");
        }
        return elements;
    }

    public void addElement(String orderId, String orderDate, Double totalAmount, UUID customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Order order = Order.builder()
                .orderId(UUID.fromString(orderId))
                .orderDate(orderDate)
                .totalAmount(totalAmount)
                .customer(customer)
                .build();

        orderRepository.save(order);
    }

    @CacheEvict
    public void deleteElement(UUID customerId, UUID orderId) {
        // Delete the element
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));

        // Remove the order from the associated customer's orders list
        order.getCustomer().getOrders().remove(order);

        // Delete the order
        orderRepository.deleteById(orderId);
    }

    public List<Order> getOrdersByOrderId(Order order) {
        return orderRepository.findByOrderId(order.getOrderId());
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}

