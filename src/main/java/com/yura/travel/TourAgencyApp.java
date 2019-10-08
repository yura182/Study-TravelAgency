package com.yura.travel;

import com.yura.travel.domain.user.User;
import com.yura.travel.encoder.PasswordEncoder;
import com.yura.travel.repository.*;
import com.yura.travel.service.*;
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
        OrderRepository orderRepository = new OrderRepositoryImpl();
        OrderService orderService = new OrderServiceImpl(orderRepository);
        new Menu(userService, tourService, orderService).run();
    }


}
