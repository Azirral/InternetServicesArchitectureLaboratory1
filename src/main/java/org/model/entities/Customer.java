package org.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer implements Comparable<Customer>, Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "customer_id", updatable = false, nullable = false)
    private UUID customerId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> orders;

    @Override
    public int hashCode() {
        return Objects.hash(customerId, name, email, orders);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(email, customer.email);
    }

    @Override
    public int compareTo(Customer otherCustomer) {
        return this.name.compareTo(otherCustomer.name);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", orders=" + orders +
                '}';
    }
}
