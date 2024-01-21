package org.model.order.dto.order;

import lombok.*;
import org.model.order.dto.customer.GetCustomerResponse;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class GetOrderResponse {
    private UUID id;
    private String date;
    private Double amount;
    private GetCustomerResponse customer;
}