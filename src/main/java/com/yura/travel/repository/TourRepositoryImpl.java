package com.yura.travel.repository;

import com.yura.travel.domain.tour.Tour;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class TourRepositoryImpl implements TourRepository {
    private Map<Long, Tour> idToTour = new HashMap<>();

    @Override
    public Tour save(Tour tour) {
        return idToTour.put(tour.getId(), tour);
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return Optional.ofNullable(idToTour.get(id));
    }

    @Override
    public Optional<Tour> update(Tour tour) {
        return Optional.ofNullable(idToTour.replace(tour.getId(), tour));
    }

    @Override
    public Optional<Tour> deleteById(Long id) {
        return Optional.ofNullable(idToTour.remove(id));
    }

    @Override
    public List<Tour> getAll() {
        return new ArrayList<>(idToTour.values());
    }

}
