package com.yura.travel.service;

import com.yura.travel.domain.tour.*;
import com.yura.travel.exception.ArgumentIsNullException;
import com.yura.travel.exception.NoSuchTourException;
import com.yura.travel.repository.TourRepository;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class TourServiceImplTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private Tour tour1;
    private Tour tour2;

    @Mock
    private TourRepository repository;

    @InjectMocks
    private TourServiceImpl tourService;

    @After
    public void resetMock() {
        reset(repository);
    }



    private void initTour() {
        tour1 = Tour.init().withName("Spa tour").withType(TourType.HEALTH)
                .withSpecification(new TourSpecification(Food.FB, Transport.BUS, 5))
                .withPrice(300).build();

        tour2 = Tour.init().withName("Beautiful Paris").withType(TourType.SIGHTSEEING)
                .withSpecification(new TourSpecification(Food.ALL, Transport.PLANE, 2))
                .withPrice(150).build();
    }

    @Test
    public void shouldReturnListOfTours() {
        initTour();
        List<Tour> expected = Arrays.asList(tour1, tour2);
        when(repository.getAll()).thenReturn(expected);

        List<Tour> actual = tourService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnListOfToursByPrice() {
        initTour();
        List<Tour> tours= Arrays.asList(tour1, tour2);
        List<Tour> expected = new ArrayList<>();
        expected.add(tour2);
        expected.add(tour1);
        when(repository.getAll()).thenReturn(tours);

        List<Tour> actual = tourService.getAllByPrice();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnListOfToursByDuration() {
        initTour();
        List<Tour> tours= Arrays.asList(tour1, tour2);
        List<Tour> expected = new ArrayList<>();
        expected.add(tour2);
        expected.add(tour1);
        when(repository.getAll()).thenReturn(tours);

        List<Tour> actual = tourService.getAllByDuration();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowArgumentIsNullException() {
        exception.expect(ArgumentIsNullException.class);
        exception.expectMessage("Arguments must be not null");
        tourService.setNewSpecification(null, null);
    }

    @Test
    public void shouldAddTour() {
        initTour();
        Tour expected = tour1;
        when(repository.save(any(Tour.class))).thenReturn(expected);
        Tour actual = tourService.add(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowArgumentIsNullExceptionWhenAdd() {
        exception.expect(ArgumentIsNullException.class);
        exception.expectMessage("Tour is null");
        tourService.add(null);
    }

    @Test
    public void shouldReturnTourById() {
        initTour();
        Tour expected = tour1;
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(expected));
        Tour actual = tourService.findById(tour1.getId());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowArgumentIsNullExceptionWhenFindById() {
        exception.expect(ArgumentIsNullException.class);
        exception.expectMessage("Argument to findById must be not null");

        tourService.findById(null);
    }

    @Test
    public void shouldThrowNoSuchTourException() {
        exception.expect(NoSuchTourException.class);
        exception.expectMessage("Tour not found");
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        tourService.findById(2L);
    }
}