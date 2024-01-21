package org.model.customer.dto;

import lombok.*;

@Data
@Builder
public class PutCustomerRequest {
    private String name;
    private String email;
}
