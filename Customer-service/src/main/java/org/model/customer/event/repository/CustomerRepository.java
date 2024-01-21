package org.model.customer.event.repository;

import org.model.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByName(String name);

    @Override
    List<Customer> findAll();

    Customer getCustomerByName(String name);
}
