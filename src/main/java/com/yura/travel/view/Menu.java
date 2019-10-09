package com.yura.travel.view;

import com.yura.travel.domain.order.Order;
import com.yura.travel.domain.tour.Food;
import com.yura.travel.domain.tour.Tour;
import com.yura.travel.domain.tour.TourSpecification;
import com.yura.travel.domain.tour.Transport;
import com.yura.travel.domain.user.User;
import com.yura.travel.service.OrderService;
import com.yura.travel.service.TourService;
import com.yura.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Menu {
    private static final String LINE = "---------";
    private static final String LANGUAGE = LINE + "Choose your language" + LINE
            + "\n1. English(default)\n2. Russian\n3.Your choose: ";
    private static final Scanner SCAN = new Scanner(System.in);
    private static final LocaleManager LOCALE = LocaleManager.LOCALE;

    private final UserService userService;
    private final TourService tourService;
    private final OrderService orderService;

    @Autowired
    public Menu(UserService userService, TourService tourService, OrderService orderService) {
        this.userService = userService;
        this.tourService = tourService;
        this.orderService = orderService;
        Helper.createDemo(tourService);
    }

    public void run() {
        System.out.print(LANGUAGE);
        int input = SCAN.nextInt();

        if (input == 2) {
            LOCALE.setLocale("ru");
            firstMenu();
        } else {
            LOCALE.setLocale("en");
            firstMenu();
        }
    }

    private void firstMenu() {
        System.out.println();
        System.out.println(LINE + LOCALE.getString("menu") + LINE);
        System.out.println(LOCALE.getString("register"));
        System.out.println(LOCALE.getString("login"));
        System.out.println(LOCALE.getString("exit"));
        int input = getInput();

        if (input < 0 || input > 2) {
            System.out.println(LOCALE.getString("wrong.input"));
            firstMenu();
        } else if (input == 0) {
            exit();
        } else if (input == 1) {
            registerMenu();
        } else {
            loginMenu();
        }
    }

    private void mainMenu(User user) {
        System.out.println();
        System.out.println(LINE + LOCALE.getString("menu") + LINE);
        System.out.println(LOCALE.getString("all.tours"));
        System.out.println(LOCALE.getString("tours.by.price"));
        System.out.println(LOCALE.getString("tours.by.duration"));
        System.out.println(LOCALE.getString("exit"));
        int input = getInput();

        if (input < 0 || input > 3) {
            System.out.println(LOCALE.getString("wrong.input"));
            mainMenu(user);
        } else if (input == 0) {
            exit();
        } else {
            switch (input) {
                case 1:
                    allToursMenu(user);
                    break;
                case 2:
                    toursByPriceMenu(user);
                    break;
                case 3:
                    toursByDurationMenu(user);
                    break;
                default:
                    break;
            }
        }
    }

    private void toursByDurationMenu(User user) {
        List<Tour> allToursSorted = tourService.getAllByDuration();
        System.out.println();
        System.out.println(LOCALE.getString("tours.by.duration.header"));
        Helper.printHeader();
        allToursSorted.forEach(System.out::println);
        chooseTourMenu(user);
    }

    private void toursByPriceMenu(User user) {
        List<Tour> allToursSorted = tourService.getAllByPrice();
        System.out.println();
        System.out.println(LOCALE.getString("tours.by.price.header"));
        Helper.printHeader();
        allToursSorted.forEach(System.out::println);
        chooseTourMenu(user);
    }

    private void allToursMenu(User user) {
        List<Tour> allTours = tourService.getAll();
        System.out.println();
        Helper.printHeader();
        allTours.forEach(System.out::println);
        chooseTourMenu(user);
    }

    private void chooseTourMenu(User user) {
        System.out.println(LOCALE.getString("choose.tour"));
        long tourId = getLongInput();
        System.out.println(LOCALE.getString("book"));
        System.out.println(LOCALE.getString("back"));
        System.out.println(LOCALE.getString("exit"));
        int input = getInput();

        if (input == 1) {
            bookMenu(user, tourId);
        } else if (input == 2) {
            mainMenu(user);
        } else {
            exit();
        }
    }

    private void bookMenu(User user, long tourId) {
        System.out.println();
        Helper.printHeader();
        Tour tour = tourService.findById(tourId);
        System.out.println(tour);
        System.out.println(LOCALE.getString("change.details"));
        System.out.println(LOCALE.getString("book.tour"));
        int input = getInput();
        TourSpecification tourSpecification = tour.getTourSpecification();

        if (input == 1) {
            tourSpecification = changeMenu(tourSpecification);
        } else if (input == 2) {
            bookedMenu(tour, tourSpecification, user);
        } else {
            mainMenu(user);
        }

        bookedMenu(tour, tourSpecification, user);

    }

    private void bookedMenu(Tour tour, TourSpecification tourSpecification, User user) {
        Tour bookedTour = tourService.setNewSpecification(tour, tourSpecification);
        Order order = new Order(user, bookedTour);
        orderService.add(order);
        System.out.println();
        System.out.println(LOCALE.getString("your.order"));
        Order clientOrder = orderService.findById(order.getId());
        System.out.println(clientOrder);
        System.out.println();
        System.out.println(LOCALE.getString("main.menu.order"));
        System.out.println(LOCALE.getString("exit"));
        int input = getInput();
        if (input == 1) {
            mainMenu(user);
        } else {
            exit();
        }
    }

    private TourSpecification changeMenu(TourSpecification tourSpecification) {
        Transport transport = tourSpecification.getTransport();
        Food food = tourSpecification.getFood();
        int duration = tourSpecification.getDuration();

        while (true) {
            changeMenuChoice();
            int input = getInput();
            if (input == 1) {
                transport = changeTransportMenu(transport);
            } else if (input == 2) {
                food = changeFoodMenu(food);
            } else if (input == 3) {
                duration = changeDurationMenu(duration);
            } else {
                break;
            }
        }
        return getTourSpec(food, transport, duration);
    }

    private void changeMenuChoice() {
        System.out.println();
        System.out.println(LOCALE.getString("change.transport"));
        System.out.println(LOCALE.getString("change.food"));
        System.out.println(LOCALE.getString("change.duration"));
        System.out.println(LOCALE.getString("book.change.menu"));
    }

    private TourSpecification getTourSpec(Food food, Transport transport, int duration) {
        return new TourSpecification(food, transport, duration);
    }

    private Transport changeTransportMenu(Transport transport) {
        System.out.println();
        System.out.println(LOCALE.getString("plane"));
        System.out.println(LOCALE.getString("bus"));
        System.out.println(LOCALE.getString("train"));
        int input = getInput();

        switch (input) {
            case 1:
                transport = Transport.PLANE;
                break;
            case 2:
                transport = Transport.BUS;
                break;
            case 3:
                transport = Transport.TRAIN;
                break;
            default:
                exit();
                break;
        }
        return transport;
    }

    private Food changeFoodMenu(Food food) {
        System.out.println();
        System.out.println(LOCALE.getString("bb"));
        System.out.println(LOCALE.getString("hb"));
        System.out.println(LOCALE.getString("fb"));
        System.out.println(LOCALE.getString("all"));
        int input = getInput();

        switch (input) {
            case 1:
                food = Food.BB;
                break;
            case 2:
                food = Food.HB;
                break;
            case 3:
                food = Food.FB;
                break;
            case 4:
                food = Food.ALL;
            default:
                break;
        }
        return food;
    }

    private int changeDurationMenu(int duration) {
        System.out.println();
        System.out.println(LOCALE.getString("change.duration.menu"));
        int input = getInput();

        if (input >= 0) {
            duration = input;
        }

        return duration;
    }

    private void registerMenu() {
        List<String> data = new ArrayList<>();
        System.out.println();
        System.out.println(LINE + LOCALE.getString("registration") + LINE);
        System.out.print(LOCALE.getString("name") + " ");
        data.add(SCAN.next());
        System.out.print(LOCALE.getString("surname") + " ");
        data.add(SCAN.next());
        System.out.print("Email: ");
        data.add(SCAN.next());
        System.out.print(LOCALE.getString("phone") + " ");
        data.add(SCAN.next());
        System.out.print(LOCALE.getString("password") + " ");
        data.add(SCAN.next());

        try {
            userService.register(User.init()
                    .withName(data.get(0))
                    .withSurname(data.get(1))
                    .withEmail(data.get(2))
                    .withPhone(data.get(3))
                    .withPassword(data.get(4)).build());
            System.out.println(LOCALE.getString("user.registered"));
            loginMenu();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            firstMenu();
        }
    }

    private void loginMenu() {
        System.out.println();
        System.out.println(LINE + LOCALE.getString("login.header") + LINE);
        System.out.print("Email: ");
        String email = SCAN.next();
        System.out.print(LOCALE.getString("password") + " ");
        String password = (SCAN.next());

        try {
            User user = userService.login(email, password);
            System.out.println(user);
            mainMenu(user);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            loginMenu();
        }
    }

    private int getInput() {
        System.out.print(LOCALE.getString("choose"));
        return SCAN.nextInt();
    }

    private long getLongInput() {
        System.out.print(LOCALE.getString("choose"));
        return SCAN.nextLong();
    }

    private void exit() {
        System.out.println(LOCALE.getString("bye"));
        System.exit(0);
    }
}
