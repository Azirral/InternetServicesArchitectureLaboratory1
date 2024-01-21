package org.model.customer.dto;

import lombok.*;
import java.util.UUID;

@Data
@Builder
public class GetCustomerResponse {
    private UUID id;
    private String name;
    private String email;
}