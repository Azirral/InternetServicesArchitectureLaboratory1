package org.model.customer.controller.impl;

import lombok.extern.java.Log;
import org.model.customer.controller.api.CustomerController;
import org.model.customer.dto.GetCustomerResponse;
import org.model.customer.dto.GetCustomersResponse;
import org.model.customer.function.CustomerToResponseFunction;
import org.model.customer.function.CustomersToResponseFunction;
import org.model.customer.service.api.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.UUID;

@RestController
@Log
public class CustomerDefaultController implements CustomerController {

    private final CustomerService service;
    private final CustomerToResponseFunction customerToResponse;
    private final CustomersToResponseFunction customersToResponse;

    @Autowired
    public CustomerDefaultController(
            CustomerService service,
            CustomerToResponseFunction customerToResponse,
            CustomersToResponseFunction customersToResponse
    ) {
        this.service = service;
        this.customerToResponse = customerToResponse;
        this.customersToResponse = customersToResponse;
    }

    @Override
    public GetCustomersResponse getCustomers() {
        return customersToResponse.apply(service.findAll());
    }

    @Override
    public GetCustomerResponse getCustomer(UUID id) {
        return service.find(id)
                .map(customerToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteCustomer(UUID id) {
        service.find(id)
                .ifPresentOrElse(
                        customer -> service.delete(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }
}