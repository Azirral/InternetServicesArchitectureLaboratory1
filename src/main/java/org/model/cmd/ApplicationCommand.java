package org.model.cmd;
import org.model.customer.service.CustomerService;
import org.model.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
public class ApplicationCommand implements CommandLineRunner {
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public ApplicationCommand(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;
        main_loop:
        while (true) {
            System.out.println("Available commands:");
            System.out.println("1. List Categories");
            System.out.println("2. List Elements");
            System.out.println("3. Add New Element");
            System.out.println("4. Delete Existing Element");
            System.out.println("5. Stop the Application");

            command = scanner.next();
            switch (command) {
                case "1" -> {
                    // List Categories
                    customerService.listCategories().forEach(System.out::println);
                }
                case "2" -> {
                    // List Elements
                    orderService.listElements().forEach(System.out::println);
                }
                case "3" -> {
                    // Add New Element
                    System.out.print("Enter the order ID: ");
                    String orderId = scanner.next();
                    System.out.print("Enter the order date: ");
                    String orderDate = scanner.next();
                    System.out.print("Enter the total amount: ");
                    Double totalAmount = scanner.nextDouble();
                    System.out.print("Enter the customer ID for the element: ");
                    String customerId = scanner.next();
                    orderService.addElement(orderId, orderDate, totalAmount, UUID.fromString(customerId));
                    System.out.println("Element added successfully.");
                }
                case "4" -> {
                    // Delete Existing Element
                    System.out.print("Enter the order ID to delete: ");
                    String orderId = scanner.next();
                    System.out.print("Enter the customer ID to delete: ");
                    String customerId = scanner.next();
                    orderService.deleteElement(UUID.fromString(customerId), UUID.fromString(orderId));
                    System.out.println("Element deleted successfully.");
                }
                case "5" -> {
                    // Stop the Application
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break main_loop;
                }
                default -> {
                    System.out.println("Invalid command. Please enter a valid command.");
                }
            }
        }
    }
}
