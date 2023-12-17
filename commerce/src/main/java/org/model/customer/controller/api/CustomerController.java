package org.model.customer.controller.api;
import org.model.customer.dto.GetCustomerResponse;
import org.model.customer.dto.GetCustomersResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

public interface CustomerController {
    /**
     * @return list of customers
     */
    @GetMapping("/api/customers")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetCustomersResponse getCustomers();

    /**
     * @param id customer's id
     * @return single customer
     */
    @GetMapping("/api/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetCustomerResponse getCustomer(
            @PathVariable("id") UUID id
    );

    /**
     * Deletes selected customer.
     *
     * @param id customer's id
     */
    @DeleteMapping("/api/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(
            @PathVariable("id") UUID id
    );

}