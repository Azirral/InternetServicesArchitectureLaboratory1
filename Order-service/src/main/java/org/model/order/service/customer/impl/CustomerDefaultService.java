package org.model.order.service.customer.impl;

import lombok.RequiredArgsConstructor;
import org.model.order.entity.Customer;
import org.model.order.exception.CustomerNotFoundException;
import org.model.order.mapper.CustomerMapper;
import org.model.order.repository.CustomerRepository;
import org.model.order.dto.customer.GetCustomerResponse;
import org.model.order.dto.customer.PutCustomerRequest;
import org.model.order.service.customer.api.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerDefaultService implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void addCustomer(GetCustomerResponse getCustomerResponse) {
        Customer customer = CustomerMapper.mapToCustomer(getCustomerResponse);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + id));
        customerRepository.delete(customer);
    }

    @Override
    public List<GetCustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerMapper::mapToGetCustomerResponse).toList();
    }

    @Override
    public GetCustomerResponse updateCustomer(UUID id, PutCustomerRequest putCustomerRequest) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + id));
        customer.setName(putCustomerRequest.getName());
        customerRepository.save(customer);
        return CustomerMapper.mapToGetCustomerResponse(customer);
    }

    @Override
    public GetCustomerResponse getCustomerById(UUID id) {
        return CustomerMapper.mapToGetCustomerResponse(customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + id)));
    }
}