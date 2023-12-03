package org.model.customer.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerCollectionDto {
    private UUID customerId;
    private String name;
}