package org.model.customer.service.impl;

import lombok.RequiredArgsConstructor;
import org.model.customer.dto.GetCustomerResponse;
import org.model.customer.dto.GetCustomersResponse;
import org.model.customer.dto.PutCustomerRequest;
import org.model.customer.entity.Customer;
import org.model.customer.event.repository.CustomerRepository;
import org.model.customer.event.repository.api.CustomerRestRepository;
import org.model.customer.exception.CustomerNotFoundException;
import org.model.customer.mapper.CustomerMapper;
import org.model.customer.service.api.CustomerService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerRestRepository customerRestRepository;

    @Override
    public void addCustomer(GetCustomerResponse getCustomerResponse) {
        Customer customer = CustomerMapper.mapToCustomer(getCustomerResponse);
        customerRestRepository.addCustomer(getCustomerResponse);
        customerRepository.save(customer);
    }
    @Override
    public GetCustomersResponse getAllCustomers() {
        return CustomerMapper.mapToGetCustomersResponse(customerRepository.findAll());
    }
    @Override
    public GetCustomerResponse getCustomerById(UUID id) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + id));
        return CustomerMapper.mapToGetCustomerResponse(customer);
    }

    @Override
    public void deleteCustomer(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + id));
        customerRestRepository.delete(id);
        customerRepository.delete(customer);
    }


    @Override
    public GetCustomerResponse updateCustomer(UUID id, PutCustomerRequest putCustomerRequest) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + id));

        customer.setName(putCustomerRequest.getName());
        if (!customer.getName().equalsIgnoreCase(putCustomerRequest.getName())) {
            customerRestRepository.updateName(id, putCustomerRequest);
        }
        customer.setEmail(putCustomerRequest.getEmail());

        customerRepository.save(customer);

        return CustomerMapper.mapToGetCustomerResponse(customer);
    }

}