package org.model.order.repository;
import org.model.customer.entity.Customer;
import org.model.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByOrderId(UUID orderId);
    /**
     * Finds a single order by ID and customer ID.
     *
     * @param OrderId          order's ID
     * @param customer  customer
     * @return container (can be empty) with the order
     */
    Optional<Order> findByOrderIdAndCustomer(UUID OrderId, Customer customer);


    List<Order> findAllByCustomer(Customer customer);
}
