package org.model.order.repository;
import org.model.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Override
    List<Order> findAll();

    @Query("select o from Order o where o.date = :date and o.amount = :amount")
    Optional<Order> findOrderByDateAndAmount(
            @Param("date") String date,
            @Param("amount") double amount);
    Optional<Order> findOrderByDate(String Date);
}