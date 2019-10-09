package com.yura.travel.service;

import com.yura.travel.domain.order.Order;
import com.yura.travel.domain.tour.Tour;
import com.yura.travel.domain.tour.TourSpecification;
import com.yura.travel.exception.ArgumentIsNullException;
import com.yura.travel.exception.NoSuchOrderException;
import com.yura.travel.repository.OrderRepository;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    OrderRepository orderRepository;

    @Mock
    Order order;

    @InjectMocks
    OrderServiceImpl orderService;

    @After
    public void resetMock() {
        reset(orderRepository);
        reset(order);
    }

    @Test
    public void shouldAddOrder() {
        Order expected = order;
        when(orderRepository.save(any(Order.class))).thenReturn(expected);
        Order actual = orderService.add(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowArgumentIsNullException() {
        exception.expect(ArgumentIsNullException.class);
        exception.expectMessage("Order is null");
        orderService.add(null);
    }

    @Test
    public void shouldThrowArgumentIsNullExceptionInFindById() {
        exception.expect(ArgumentIsNullException.class);
        exception.expectMessage("Id is null");
        orderService.findById(null);
    }

    @Test
    public void shouldThrowNoSuchOrderException() {
        exception.expect(NoSuchOrderException.class);
        exception.expectMessage("Order not found");
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        orderService.findById(10L);
    }

    @Test
    public void shouldReturnOrder() {
        Order expected = order;
        System.out.println(order.getId());
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(expected));
        Order actual = orderService.findById(1L);

        assertEquals(expected, actual);
    }
}