package org.model.order.service.customer.api;


import org.model.order.dto.customer.GetCustomerResponse;
import org.model.order.dto.customer.PutCustomerRequest;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    void addCustomer(GetCustomerResponse getCustomerResponse);

    void deleteCustomer(UUID id);

    List<GetCustomerResponse> getAllCustomers();

    GetCustomerResponse updateCustomer(UUID id, PutCustomerRequest putCustomerRequest);

    GetCustomerResponse getCustomerById(UUID id);
}