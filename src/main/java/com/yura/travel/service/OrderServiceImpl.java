package com.yura.travel.service;

import com.yura.travel.domain.order.Order;
import com.yura.travel.exception.ArgumentIsNullException;
import com.yura.travel.exception.NoSuchOrderException;
import com.yura.travel.repository.OrderRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order add(Order order) {
        if (order == null) {
            LOGGER.warn("Null parameter");
            throw new ArgumentIsNullException("Order is null");
        }
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        if (id == null) {
            LOGGER.warn("Null parameter");
            throw new ArgumentIsNullException("Id is null");
        }
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchOrderException("Order not found"));
    }
}
