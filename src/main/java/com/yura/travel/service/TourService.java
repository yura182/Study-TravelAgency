package com.yura.travel.service;

import com.yura.travel.domain.tour.Tour;

import java.util.List;

public interface TourService {

    List<Tour> getAll();

    Tour add(Tour tour);

    Tour findById(Long id);
}
