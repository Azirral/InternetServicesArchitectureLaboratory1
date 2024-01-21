package org.model.order.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.model.order.dto.customer.GetCustomerResponse;

import java.util.UUID;

@Data
@Builder
public class GetOrderDetailsResponse {
    private UUID id;
    private String date;
    private Double amount;
    @JsonProperty
    private GetCustomerResponse customer;
}
