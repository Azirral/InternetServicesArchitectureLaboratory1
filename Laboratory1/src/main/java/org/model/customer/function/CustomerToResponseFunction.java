package org.model.customer.function;
import org.model.customer.dto.GetCustomerResponse;
import org.model.customer.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Converts {@link Customer} to {@link GetCustomerResponse}.
 */
@Component
public class CustomerToResponseFunction implements Function<Customer, GetCustomerResponse> {

    @Override
    public GetCustomerResponse apply(Customer customer) {
        return GetCustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
    }
}
