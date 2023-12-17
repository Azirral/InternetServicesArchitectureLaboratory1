package org.model.customer.initialize;

import org.model.customer.entity.Customer;
import org.model.customer.service.api.CustomerService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InitializeData implements InitializingBean {
    private final CustomerService customerService;

    @Autowired
    public InitializeData(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (customerService.findAll().isEmpty()) {
            // Populate the database with sample data using the service classes
            Customer customer1 = Customer.builder()
                    .customerId(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                    .name("Customer 1")
                    .email("customer1@example.com")
                    .build();

            Customer customer2 = Customer.builder()
                    .customerId(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b071e4e"))
                    .name("Customer 2")
                    .email("customer2@example.com")
                    .build();

            // Save the entities to the database using the service methods
            customerService.create(customer1);
            customerService.create(customer2);
        }
    }
}
