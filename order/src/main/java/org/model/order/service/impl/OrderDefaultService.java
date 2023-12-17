package org.model.order.service.impl;
import org.model.customer.entity.Customer;
import org.model.customer.repository.CustomerRepository;
import org.model.customer.service.api.CustomerService;
import org.model.order.entity.Order;
import org.model.order.repository.OrderRepository;
import org.model.order.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderDefaultService implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderDefaultService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Order> find(UUID id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> find(Customer customer, UUID id) {
        return orderRepository.findByOrderIdAndCustomer(id, customer);
    }

    @Override
    public void create(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<List<Order>> findAllByCustomerId(UUID customerId) {
        return customerRepository.findById(customerId)
                .map(orderRepository::findAllByCustomer);
    }
}
