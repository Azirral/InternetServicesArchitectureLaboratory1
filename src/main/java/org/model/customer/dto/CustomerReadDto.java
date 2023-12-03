package org.model.customer.dto;

import lombok.*;
import org.model.order.dto.OrderReadDto;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerReadDto {
    private UUID customerId;
    private String name;
    private String email;
    private List<OrderReadDto> orders;
}