package org.model.customer.function;
import org.model.customer.dto.PatchCustomerRequest;
import org.model.customer.entity.Customer;

import java.util.function.BiFunction;

/**
 * Returns new instance of {@link Customer} based on provided value and updated with values from {@link PatchCustomerRequest}.
 */
public class UpdateCustomerWithRequestFunction implements BiFunction<Customer, PatchCustomerRequest, Customer> {

    @Override
    public Customer apply(Customer entity, PatchCustomerRequest request) {
        return Customer.builder()
                .customerId(entity.getCustomerId())
                .name(request.getName())
                .email(request.getEmail())
                .build();
    }
}