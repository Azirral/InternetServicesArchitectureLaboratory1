package org.model.customer.controller.impl;

import lombok.extern.java.Log;
import org.model.customer.controller.api.CustomerController;
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

    @Autowired
    public CustomerDefaultController(CustomerService service) {this.service = service;}

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