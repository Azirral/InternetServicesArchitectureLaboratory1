package org.model.customer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    private UUID id;

    private String name;

    private String email;
}
