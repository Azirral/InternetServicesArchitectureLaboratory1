package org.model.customer.function;

import org.model.customer.dto.PutCustomerRequest;
import org.model.customer.entity.Customer;

import java.util.UUID;
import java.util.function.BiFunction;

/**
 * Converts {@link PutCustomerRequest} to {@link Customer}.
 */
public class RequestToCustomerFunction implements BiFunction<UUID, PutCustomerRequest, Customer> {

    @Override
    public Customer apply(UUID id, PutCustomerRequest request) {
        return Customer.builder()
                .customerId(id)
                .name(request.getName())
                .email(request.getEmail())
                .build();
    }
}