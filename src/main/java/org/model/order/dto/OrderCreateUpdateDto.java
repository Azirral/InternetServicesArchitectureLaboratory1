package org.model.order.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreateUpdateDto {
    private String orderDate;
    private Double totalAmount;
}