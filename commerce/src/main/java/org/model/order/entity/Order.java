package org.model.order.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.model.customer.entity.Customer;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order implements Comparable<Order>, Serializable {
    @Id
    @Column(name = "order_id")
    private UUID orderId;
    @Column(name = "order_date")
    private String orderDate;
    @Column(name = "total_amount")
    private Double totalAmount;
    @ManyToOne
    @JoinColumn(name = "customer_id") // Define the foreign key column
    private Customer customer;

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderDate, totalAmount, customer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(totalAmount, order.totalAmount) &&
                Objects.equals(customer, order.customer);
    }

    @Override
    public int compareTo(Order otherOrder) {
        return this.orderId.compareTo(otherOrder.orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", totalAmount=" + totalAmount +
                ", customer=" + customer.getCustomerId() +
                '}';
    }
}
