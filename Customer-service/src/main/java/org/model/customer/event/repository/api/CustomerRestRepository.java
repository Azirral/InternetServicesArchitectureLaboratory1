package org.model.customer.event.repository.api;

import org.model.customer.dto.GetCustomerResponse;
import org.model.customer.dto.PutCustomerRequest;

import java.util.UUID;

public interface CustomerRestRepository {
    void delete(UUID id);

    void updateName(UUID id, PutCustomerRequest putCustomerRequest);

    void addCustomer(GetCustomerResponse getCustomerResponse);
}
