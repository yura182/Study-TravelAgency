package com.yura.travel.service;

import com.yura.travel.domain.tour.Tour;
import com.yura.travel.domain.tour.TourSpecification;

import java.util.List;

public interface TourService {

    List<Tour> getAll();

    List<Tour> getAllByPrice();

    List<Tour> getAllByDuration();

    Tour setNewSpecification(Tour tour, TourSpecification specification);

    Tour add(Tour tour);

    Tour findById(Long id);
}
