package org.model.order.dto.customer;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetCustomerResponse {
    private UUID id;
    private String name;
}
