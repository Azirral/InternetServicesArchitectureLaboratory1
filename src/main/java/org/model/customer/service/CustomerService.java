package org.model.customer.service;

import org.model.customer.entity.Customer;
import org.model.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public List<Customer> listCategories() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            System.out.println("No categories found.");
        }
        return customers;
    }


    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }
}
