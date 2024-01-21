package org.model.order.dto.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PutCustomerRequest {
    private String name;
}
