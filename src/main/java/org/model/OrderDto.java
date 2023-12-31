package org.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto implements Comparable<OrderDto> {
    private String orderId;
    private String orderDate;
    private Double totalAmount;
    private String customer;

    public int compareTo(OrderDto otherOrderDto) {
        return this.orderId.compareTo(otherOrderDto.orderId);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", totalAmount=" + totalAmount +
                ", customer='" + customer + '\'' +
                '}';
    }
}
