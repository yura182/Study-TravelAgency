package com.yura.travel.domain.order;

import com.yura.travel.domain.tour.Tour;
import com.yura.travel.domain.user.User;

public class Order {
    private static Long nextId = 1L;

    private final Long id;
    private final User user;
    private final Tour tour;

    public Order(User user, Tour tour) {
        this.id = nextId++;
        this.user = user;
        this.tour = tour;
    }

    public User getUser() {
        return user;
    }

    public Tour getTour() {
        return tour;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return tour.toString();
    }

}
