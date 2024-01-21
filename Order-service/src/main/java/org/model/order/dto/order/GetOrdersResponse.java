package org.model.order.dto.order;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class GetOrdersResponse {
    @Singular
    private List<GetOrderResponse> orders;
}