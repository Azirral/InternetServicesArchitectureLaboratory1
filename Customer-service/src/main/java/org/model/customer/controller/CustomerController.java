package org.model.customer.controller;


import lombok.RequiredArgsConstructor;
import org.model.customer.dto.GetCustomerResponse;
import org.model.customer.dto.GetCustomersResponse;
import org.model.customer.dto.PutCustomerRequest;
import org.model.customer.service.api.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> addNewCustomer(@RequestBody GetCustomerResponse getCustomerResponse) {
        getCustomerResponse.setId(UUID.randomUUID());
        customerService.addCustomer(getCustomerResponse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCustomerResponse> updateCustomer(
            @PathVariable UUID id,
            @RequestBody PutCustomerRequest putCustomerRequest
    ) {
        GetCustomerResponse updatedCustomer = customerService.updateCustomer(id, putCustomerRequest);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<GetCustomersResponse> getAllCustomers() {
        GetCustomersResponse customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCustomerResponse> getCustomerById(@PathVariable UUID id) {
        GetCustomerResponse customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
