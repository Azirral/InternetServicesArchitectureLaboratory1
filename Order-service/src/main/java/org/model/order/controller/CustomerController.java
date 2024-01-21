package org.model.order.controller;

import lombok.RequiredArgsConstructor;
import org.model.order.service.customer.api.CustomerService;
import org.model.order.dto.customer.GetCustomerResponse;
import org.model.order.dto.customer.PutCustomerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<Void> addNewCustomer(@RequestBody GetCustomerResponse getCustomerResponse) {
        service.addCustomer(getCustomerResponse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCustomerResponse> updateCustomer(@PathVariable UUID id,@RequestBody  PutCustomerRequest putCustomerRequest
    ) {
        GetCustomerResponse updatedCustomer = service.updateCustomer(id, putCustomerRequest);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        service.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}