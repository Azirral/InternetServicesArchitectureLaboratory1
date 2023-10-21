package org.example;

import org.model.Customer;
import org.model.Order;
import org.model.OrderDto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
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

        //Task4: Use a single stream pipeline to filter, sort, and print the collection
        allOrders.stream()
                .filter(order -> order.getOrderDate().compareTo("2023-10-15") >= 0)  // Filter by a selected property (order date >= "2023-10-15")
                .sorted((order1, order2) -> Double.compare(order1.getTotalAmount(), order2.getTotalAmount()))  // Sort by a different property (totalAmount)
                .forEach(order -> System.out.println("Order: " + order.getOrderId()));

        //Task5: Use a single stream pipeline to transform, sort, and collect into a List of DTOs
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

        //Task6: Serialize and save the list of customers to a binary file
        serializeCustomers(customers, "customers.dat");

        // Read the list of customers from the binary file
        List<Customer> loadedCustomers = deserializeCustomers("customers.dat");

        if (loadedCustomers != null) {
            // Print the loaded list of customers with their elements (orders)
            for (Customer customer : loadedCustomers) {
                System.out.println("Customer: " + customer.getName());
                for (Order order : customer.getOrders()) {
                    System.out.println("   Order: " + order.getOrderId());
                }
            }
        } else {
            System.out.println("Error loading customers from the file.");
        }

        //Task7: Define the custom thread pool size
        int customThreadPoolSize = 3;

        // Create a ForkJoinPool with the specified pool size
        ForkJoinPool customThreadPool = new ForkJoinPool(customThreadPoolSize);

        // Use parallelStream to execute tasks on each category
        customers.parallelStream()
                .forEach(customer -> {
                    customThreadPool.execute(() -> {
                        System.out.println("Processing customer: " + customer.getName());
                        // Simulate workload with Thread.sleep
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                });

        // Shutdown the custom thread pool
        customThreadPool.shutdown();
        try {
            customThreadPool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    // Serialize and save the list of customers to a binary file
    private static void serializeCustomers(List<Customer> customers, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(customers);
            System.out.println("Customers serialized and saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read the list of customers from the binary file
    private static List<Customer> deserializeCustomers(String fileName) {
        List<Customer> loadedCustomers = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)) ){
            loadedCustomers = (List<Customer>) ois.readObject();
            System.out.println("Customers loaded from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedCustomers;
    }
}


