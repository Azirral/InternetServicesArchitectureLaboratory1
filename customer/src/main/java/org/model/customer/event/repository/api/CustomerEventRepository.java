package org.model.customer.event.repository.api;

import java.util.UUID;

public interface CustomerEventRepository {
    void delete(UUID customerId);
}
