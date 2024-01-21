package org.model.order.mapper;

import org.model.order.dto.order.GetOrderDetailsResponse;
import org.model.order.dto.order.GetOrderResponse;
import org.model.order.dto.order.GetOrdersResponse;
import org.model.order.dto.order.PutOrderRequest;
import org.model.order.entity.Customer;
import org.model.order.entity.Order;

import java.util.List;
import java.util.UUID;

public class OrderMapper {
    public static GetOrderResponse mapToGetOrderResponse(Order order) {

        return GetOrderResponse.builder()
                .customer(CustomerMapper.mapToGetCustomerResponse(order.getCustomer()))
                .id(order.getId())
                .date(order.getDate())
                .amount(order.getAmount())
                .build();
    }

    public static GetOrderDetailsResponse mapToGetOrderDetailsResponse(Order order) {

        return GetOrderDetailsResponse.builder()
                .customer(CustomerMapper.mapToGetCustomerResponse(order.getCustomer()))
                .id(order.getId())
                .date(order.getDate())
                .amount(order.getAmount())
                .build();
    }

    public static Order mapToOrder(GetOrderDetailsResponse getOrderResponse, Customer customer) {
        return Order.builder()
                .id(getOrderResponse.getId())
                .customer(customer)
                .date(getOrderResponse.getDate())
                .amount(getOrderResponse.getAmount())
                .build();
    }

    public static Order mapToOrder(PutOrderRequest putOrderRequest, Customer customer, UUID uuid) {
        return Order.builder()
                .id(uuid)
                .customer(customer)
                .date(putOrderRequest.getDate())
                .amount(putOrderRequest.getAmount())
                .build();
    }

    public static GetOrdersResponse mapToGetOrdersResponse(List<Order> orders) {
        return GetOrdersResponse.builder()
                .orders(orders.stream()
                        .map(OrderMapper::mapToGetOrderResponse)
                        .toList())
                .build();
    }
}
