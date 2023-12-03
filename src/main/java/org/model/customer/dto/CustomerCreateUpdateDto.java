package org.model.customer.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerCreateUpdateDto {
    private String name;
    private String email;
}