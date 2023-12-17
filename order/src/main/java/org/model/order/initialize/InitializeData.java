package org.model.order.initialize;

import org.model.customer.entity.Customer;
import org.model.customer.service.api.CustomerService;
import org.model.order.entity.Order;
import org.model.order.service.api.OrderService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class InitializeData implements InitializingBean{
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public InitializeData(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @Override
    public void afterPropertiesSet() throws Exception{
        // Populate the database with sample data using the service classes
        if(orderService.findAll().isEmpty()) {
            Customer customer1 = Customer.builder()
                    .customerId(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                    .build();

            Customer customer2 = Customer.builder()
                    .customerId(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b071e4e"))
                    .build();

            // Save the entities to the database using the service methods
            customerService.create(customer1);
            customerService.create(customer2);

            Order order1 = Order.builder()
                    .orderId(UUID.fromString("525d3e7b-bb1f-4c13-bf17-926d1a12e4c0"))
                    .orderDate("2023-10-15")
                    .totalAmount(100.0)
                    .customer(customer1)
                    .build();

            Order order2 = Order.builder()
                    .orderId(UUID.fromString("cc0b0577-bb6f-45b7-81d6-3db88e6ac19f"))
                    .orderDate("2023-10-16")
                    .totalAmount(150.0)
                    .customer(customer2)
                    .build();
            orderService.create(order1);
            orderService.create(order2);
        }
    }
}
