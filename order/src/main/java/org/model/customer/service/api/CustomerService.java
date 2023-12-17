package org.model.customer.service.api;


import org.model.customer.entity.Customer;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    Optional<Customer> find(UUID id);

    void create(Customer customer);

    void delete(UUID id);
}