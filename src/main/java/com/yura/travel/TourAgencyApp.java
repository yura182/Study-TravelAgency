package com.yura.travel;

import com.yura.travel.domain.user.User;
import com.yura.travel.encoder.PasswordEncoder;
import com.yura.travel.repository.TourRepository;
import com.yura.travel.repository.TourRepositoryImpl;
import com.yura.travel.repository.UserRepository;
import com.yura.travel.repository.UserRepositoryImpl;
import com.yura.travel.service.TourService;
import com.yura.travel.service.TourServiceImpl;
import com.yura.travel.service.UserService;
import com.yura.travel.service.UserServiceImpl;
import com.yura.travel.validator.UserValidator;
import com.yura.travel.view.Menu;

public class TourAgencyApp {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();
        UserValidator userValidator = new UserValidator();
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        UserService userService = new UserServiceImpl(userRepository, userValidator, passwordEncoder);
        TourRepository tourRepository = new TourRepositoryImpl();
        TourService tourService = new TourServiceImpl(tourRepository);
        new Menu(userService, tourService).run();
    }


}
