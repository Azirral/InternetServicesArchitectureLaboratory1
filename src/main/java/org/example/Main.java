package org.example;

import org.model.Customer;
import org.model.Order;
import org.model.OrderDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Create a list of customers
        List<Customer> customers = createCustomers();

        //Task2: Print all customers and their associated orders
        customers.forEach(customer -> System.out.println(customer.toString()));

        //Task 3: Use a stream pipeline to collect all elements (orders) into a Set
        Set<Order> allOrders = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .collect(Collectors.toSet());

        // Print the Set of all orders
        allOrders.forEach(order -> System.out.println("Order: " + order.getOrderId()));

        //Task3: Use a single stream pipeline to filter, sort, and print the collection
        allOrders.stream()
                .filter(order -> order.getOrderDate().compareTo("2023-10-15") >= 0)  // Filter by a selected property (order date >= "2023-10-15")
                .sorted((order1, order2) -> Double.compare(order1.getTotalAmount(), order2.getTotalAmount()))  // Sort by a different property (totalAmount)
                .forEach(order -> System.out.println("Order: " + order.getOrderId()));

        //Task4: Use a single stream pipeline to transform, sort, and collect into a List of DTOs
        List<OrderDto> orderDtos = allOrders.stream()
                .map(order -> OrderDto.builder()
                        .orderId(order.getOrderId())
                        .orderDate(order.getOrderDate())
                        .totalAmount(order.getTotalAmount())
                        .customer(order.getCustomer().getName())
                        .build())
                .sorted()
                .collect(Collectors.toList());

        // Use a second stream pipeline to print the List of DTOs
        orderDtos.forEach(dto -> System.out.println("Order DTO: " + dto));

    }

    // Create sample customers with orders
    private static List<Customer> createCustomers() {
        List<Customer> customers = new ArrayList<>();

        // Create orders and associate them with customers
        List<Order> orders1 = createOrdersForCustomer(1);
        List<Order> orders2 = createOrdersForCustomer(2);

        Customer customer1 = Customer.builder()
                .customerId(1)
                .name("Customer 1")
                .email("customer1@example.com")
                .orders(orders1)
                .build();

        Customer customer2 = Customer.builder()
                .customerId(2)
                .name("Customer 2")
                .email("customer2@example.com")
                .orders(orders2)
                .build();

        customers.add(customer1);
        customers.add(customer2);

        return customers;
    }

    // Create sample orders for a customer
    private static List<Order> createOrdersForCustomer(int customerId) {
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .customerId(customerId)
                .name("Customer " + customerId)
                .email("customer" + customerId + "@example.com")
                .build();

        orders.add(Order.builder()
                .orderId("Order" + customerId + "-1")
                .orderDate("2023-10-15")
                .totalAmount(200.0)
                .customer(customer)
                .build());

        orders.add(Order.builder()
                .orderId("Order" + customerId + "-2")
                .orderDate("2023-10-16")
                .totalAmount(150.0)
                .customer(customer)
                .build());

        return orders;
    }
}


