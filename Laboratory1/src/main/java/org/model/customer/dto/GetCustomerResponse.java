package org.model.customer.dto;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class GetCustomerResponse {
    private UUID customerId;
    private String name;
    private String email;
}