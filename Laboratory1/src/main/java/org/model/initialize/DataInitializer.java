package org.model.initialize;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;
import org.model.customer.entity.Customer;
import org.model.customer.service.api.CustomerService;
import org.model.order.entity.Order;
import org.model.order.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer implements InitializingBean{
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public DataInitializer(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @Override
    public void afterPropertiesSet() throws Exception{
        // Populate the database with sample data using the service classes
        Customer customer1 = Customer.builder()
                .customerId(UUID.randomUUID())
                .name("Customer 1")
                .email("customer1@example.com")
                .build();

        Customer customer2 = Customer.builder()
                .customerId(UUID.randomUUID())
                .name("Customer 2")
                .email("customer2@example.com")
                .build();

        Order order1 = Order.builder()
                .orderId(UUID.randomUUID())
                .orderDate("2023-10-15")
                .totalAmount(100.0)
                .customer(customer1)
                .build();

        Order order2 = Order.builder()
                .orderId(UUID.randomUUID())
                .orderDate("2023-10-16")
                .totalAmount(150.0)
                .customer(customer2)
                .build();

        // Save the entities to the database using the service methods
        customerService.create(customer1);
        customerService.create(customer2);
        orderService.create(order1);
        orderService.create(order2);
    }
}
