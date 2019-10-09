package com.yura.travel.service;

import com.yura.travel.domain.order.Order;

public interface OrderService {

    Order add(Order order);

    Order findById(Long id);
}
