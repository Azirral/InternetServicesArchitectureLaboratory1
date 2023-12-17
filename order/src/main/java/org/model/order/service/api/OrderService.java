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
     * @return all available orders
     */
    List<Order> findAll();

    /**
     * @param id   order's id
     * @param customer existing customer
     * @return selected character for user
     */
    Optional<Order> find(Customer customer, UUID id);

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