package com.yura.travel.service;

import com.yura.travel.domain.order.Order;
import com.yura.travel.exception.NoSuchOrderException;
import com.yura.travel.repository.OrderRepository;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order add(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchOrderException("Order not found"));
    }
}
