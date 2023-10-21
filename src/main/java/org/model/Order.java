package org.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order implements Comparable<Order> {
    private String orderId;
    private String orderDate;
    private Double totalAmount;
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
