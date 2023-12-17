package org.model.customer.service.api;


import org.model.customer.entity.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    List<Customer> findAll();

    Optional<Customer> find(UUID id);

    Optional<Customer> find(String name);

    void create(Customer customer);

    void delete(UUID id);
}