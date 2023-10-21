package org.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer implements Comparable<Customer>, Serializable {
    private Integer customerId;
    private String name;
    private String email;
    private List<Order> orders;

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
        return this.customerId.compareTo(otherCustomer.customerId);
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
