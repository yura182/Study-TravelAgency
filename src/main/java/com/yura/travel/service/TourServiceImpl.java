package com.yura.travel.service;

import com.yura.travel.domain.tour.Tour;
import com.yura.travel.domain.tour.TourSpecification;
import com.yura.travel.exception.ArgumentIsNullException;
import com.yura.travel.exception.NoSuchTourException;
import com.yura.travel.repository.TourRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;

    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public List<Tour> getAll() {
        return tourRepository.getAll();
    }

    @Override
    public List<Tour> getAllByPrice() {
        return getAll()
                .stream()
                .sorted(Comparator.comparing(Tour::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Tour> getAllByDuration() {
        return getAll()
                .stream()
                .sorted(Comparator.comparing(tour -> tour.getTourSpecification().getDuration()))
                .collect(Collectors.toList());
    }

    @Override
    public Tour setNewSpecification(Tour tour, TourSpecification specification) {
        return new Tour(tour, specification);
    }

    @Override
    public Tour add(Tour tour) {
        if (tour == null) {
            throw new ArgumentIsNullException("Tour is null");
        }

        return tourRepository.save(tour);
    }

    @Override
    public Tour findById(Long id) {
        if (id == null) {
            throw new ArgumentIsNullException("Argument to findById must be not null");
        }
        return tourRepository.findById(id)
                .orElseThrow(() -> new NoSuchTourException("Tour not found"));
    }
}
