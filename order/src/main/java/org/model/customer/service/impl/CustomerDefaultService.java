package org.model.customer.service.impl;

import org.model.customer.entity.Customer;
import org.model.customer.repository.CustomerRepository;
import org.model.customer.service.api.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerDefaultService implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerDefaultService(CustomerRepository customerRepository) {this.customerRepository = customerRepository;}

    @Override
    public Optional<Customer> find(UUID id) {
        return customerRepository.findById(id);
    }

    @Override
    public void create(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(UUID id) {
        customerRepository.findById(id).ifPresent(customerRepository::delete);
    }
}