package org.model.customer.initialize;

import jakarta.annotation.PostConstruct;
import org.model.customer.entity.Customer;
import org.model.customer.event.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DataLoader{
    private final CustomerRepository customerRepository;

    public DataLoader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static List<Customer> loadCustomers() {
        List<Customer> listOfCustomers = new ArrayList<>();
        listOfCustomers.add(Customer.builder()
                .id(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                .name("Customer 1")
                .email("customer1@example.com")
                .build());
        listOfCustomers.add(Customer.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b071e4e"))
                .name("Customer 2")
                .email("customer2@example.com")
                .build());

        return listOfCustomers;
    }


    @PostConstruct
    public void persistData() {
        List<Customer> listOfCustomers = DataLoader.loadCustomers();
        customerRepository.saveAll(listOfCustomers);
    }
}
