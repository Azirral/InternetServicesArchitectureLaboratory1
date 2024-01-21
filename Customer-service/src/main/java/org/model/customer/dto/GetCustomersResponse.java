package org.model.customer.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class GetCustomersResponse {
    @Singular
    private List<GetCustomerResponse> customers;
}