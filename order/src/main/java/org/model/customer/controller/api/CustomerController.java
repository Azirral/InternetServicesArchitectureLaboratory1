package org.model.customer.controller.api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

public interface CustomerController {
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