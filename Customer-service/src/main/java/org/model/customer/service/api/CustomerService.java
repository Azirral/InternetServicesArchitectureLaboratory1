package org.model.customer.service.api;


import org.model.customer.dto.GetCustomerResponse;
import org.model.customer.dto.GetCustomersResponse;
import org.model.customer.dto.PutCustomerRequest;
import org.model.customer.entity.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {


    void addCustomer(GetCustomerResponse getCustomerResponse);

    GetCustomersResponse getAllCustomers();

    GetCustomerResponse getCustomerById(UUID id);
    void deleteCustomer(UUID id);

    GetCustomerResponse updateCustomer(UUID id, PutCustomerRequest putCustomerRequest);

}