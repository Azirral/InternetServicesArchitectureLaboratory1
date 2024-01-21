package org.model.order.mapper;

import org.model.order.dto.customer.GetCustomerResponse;
import org.model.order.entity.Customer;

public class CustomerMapper {
    
    public static Customer mapToCustomer(GetCustomerResponse getCustomerResponse) {
        return Customer.builder()
                .id(getCustomerResponse.getId())
                .name(getCustomerResponse.getName())
                .build();
    }

    public static GetCustomerResponse mapToGetCustomerResponse(Customer customer) {
        return GetCustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .build();
    }
}
