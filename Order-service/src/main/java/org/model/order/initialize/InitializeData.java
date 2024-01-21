package org.model.order.initialize;

import jakarta.annotation.PostConstruct;
import org.model.order.entity.Customer;
import org.model.order.repository.CustomerRepository;
import org.model.order.repository.OrderRepository;
import org.model.order.service.customer.api.CustomerService;
import org.model.order.entity.Order;
import org.model.order.service.order.api.OrderService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
@Component
public class InitializeData {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public InitializeData(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    public void persistData() {
        List<Customer> customers = loadCustomers();
        customerRepository.saveAll(customers);
        List<Order> listOfOrders = loadOrders(customers);
        loadOrdersToCustomer(customers, listOfOrders);
        orderRepository.saveAll(listOfOrders);
        customerRepository.saveAll(customers);

    }

    public void loadOrdersToCustomer(List<Customer> customers, List<Order> listOfOrders) {
        customers.get(0).setOrders(Arrays.asList(listOfOrders.get(0), listOfOrders.get(1)));
        customers.get(1).setOrders(Arrays.asList(listOfOrders.get(2), listOfOrders.get(3)));
    }


    public List<Order> loadOrders(List<Customer> customers) {
        List<Order> listOfOrders = new ArrayList<>();
        List<UUID> uuids = Arrays.asList(
                UUID.fromString("525d3e7b-bb1f-4c13-bf17-926d1a12e4c0"),
                UUID.fromString("cc0b0577-bb6f-45b7-81d6-3db88e6ac19f"),
                UUID.fromString("04dbe36d-e6ca-4030-a3e9-b202967de4cd"),
                UUID.fromString("9d1d3e0a-bb79-4de4-a836-87ca02216846")

        );
        listOfOrders.add(Order.builder().id(uuids.get(0))
                .date("2023-10-15").amount(100.0)
                .customer(customers.get(0)).build());
        listOfOrders.add(Order.builder().id(uuids.get(1))
                .date("2023-10-16").amount(150.0)
                .customer(customers.get(0)).build());
        listOfOrders.add(Order.builder().id(uuids.get(2))
                .date("2023-10-17").amount(120.0)
                .customer(customers.get(1)).build());
        listOfOrders.add((Order.builder().id(uuids.get(3))
                .date("2023-10-18").amount(130.0)
                .customer(customers.get(1)).build()));
        return listOfOrders;
    }

    public List<Customer> loadCustomers() {
        List<Customer> listOfCustomers = new ArrayList<>();
        listOfCustomers.add(Customer.builder().id(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                .name("Customer 1").build());

        listOfCustomers.add(Customer.builder().id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b071e4e"))
                .name("Customer 2").build());
        return listOfCustomers;
    }
}
