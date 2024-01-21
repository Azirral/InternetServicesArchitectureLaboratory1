package org.model.customer.mapper;


import org.model.customer.dto.GetCustomersResponse;
import org.model.customer.dto.GetCustomerResponse;
import org.model.customer.entity.Customer;

import java.util.List;

public class CustomerMapper {
    public static Customer mapToCustomer(GetCustomerResponse getCustomerResponse) {
        return Customer.builder()
                .id(getCustomerResponse.getId())
                .name(getCustomerResponse.getName())
                .email(getCustomerResponse.getEmail())
                .build();
    }

    public static GetCustomerResponse mapToGetCustomerResponse(Customer customer) {
        return GetCustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
    }

    public static GetCustomersResponse mapToGetCustomersResponse(List<Customer> customers) {
        return GetCustomersResponse.builder()
                .customers(customers.stream()
                        .map(CustomerMapper::mapToGetCustomerResponse)
                        .toList())
                .build();
    }
}
