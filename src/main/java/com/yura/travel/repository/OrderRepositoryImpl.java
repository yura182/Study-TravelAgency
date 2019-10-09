package com.yura.travel.repository;

import com.yura.travel.domain.order.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private Map<Long, Order> idToOrder = new HashMap<>();

    @Override
    public Order save(Order order) {
        return idToOrder.put(order.getId(), order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(idToOrder.get(id));
    }

    @Override
    public Optional<Order> update(Order order) {
        return Optional.ofNullable(idToOrder.replace(order.getId(), order));
    }

    @Override
    public Optional<Order> deleteById(Long id) {
        return Optional.ofNullable(idToOrder.remove(id));
    }
}
