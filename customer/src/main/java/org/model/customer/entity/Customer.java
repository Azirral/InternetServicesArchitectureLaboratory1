package org.model.customer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @Column(name = "customer_id", updatable = false, nullable = false)
    private UUID customerId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
}
