package org.model.customer.function;
import org.model.customer.dto.GetCustomersResponse;
import org.model.customer.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Converts {@link List<Customer>} to {@link GetCustomersResponse}.
 */
@Component
public class CustomersToResponseFunction implements Function<List<Customer>, GetCustomersResponse> {

    @Override
    public GetCustomersResponse apply(List<Customer> customers) {
        return GetCustomersResponse.builder()
                .customers(customers.stream()
                        .map(customer -> GetCustomersResponse.Customer.builder()
                                .customerId(customer.getCustomerId())
                                .name(customer.getName())
                                .email(customer.getEmail())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
