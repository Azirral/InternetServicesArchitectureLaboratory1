package org.model.order.service.api;
import org.model.customer.entity.Customer;
import org.model.order.entity.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for all business actions regarding order entity.
 */
public interface OrderService {

    /**
     * Finds a single order.
     *
     * @param id order's id
     * @return container with order
     */
    Optional<Order> find(UUID id);

    /**
     * Finds a single order by ID and customer ID.
     *
     * @param orderId          order's id
     * @param customer  customer's id
     * @return container with order
     */
    Optional<Order> find(UUID orderId, Customer customer);

    /**
     * Finds all orders.
     *
     * @return list of all orders
     */
    List<Order> findAll();

    /**
     * Finds all orders by customer ID.
     *
     * @param customer customer
     * @return list of customer's orders
     */
    List<Order> findAllByCustomer(Customer customer);

    /**
     * Creates a new order.
     *
     * @param order new order
     */
    void create(Order order);

    /**
     * Updates an existing order.
     *
     * @param order order to be updated
     */
    void update(Order order);


    /**
     * @param customerId customer id
     * @return list of characters is user exists
     */
    Optional<List<Order>> findAllByCustomerId(UUID customerId);
    /**
     * Deletes an existing order.
     *
     * @param id existing order's id to be deleted
     */
    void delete(UUID id);

    // Add other custom methods based on your business logic

}