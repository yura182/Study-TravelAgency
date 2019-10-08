package com.yura.travel.repository;

import com.yura.travel.domain.tour.Tour;

import java.util.List;

public interface TourRepository extends Repository<Tour> {
    List<Tour> getAll();
}
