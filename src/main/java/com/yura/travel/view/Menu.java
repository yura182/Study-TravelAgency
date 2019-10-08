package com.yura.travel.view;

import com.yura.travel.domain.tour.Tour;
import com.yura.travel.domain.user.User;
import com.yura.travel.service.TourService;
import com.yura.travel.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final String LINE = "---------";
    private static final String LANGUAGE = LINE + "Choose your language" +  LINE
            + "\n1. English(default)\n2. Russian\n3.Your choose: ";
    private static final Scanner SCAN = new Scanner(System.in);
    private static final LocaleManager LOCALE = LocaleManager.LOCALE;

    private final UserService userService;
    private final TourService tourService;


    public Menu(UserService studentService, TourService tourService) {
        this.userService = studentService;
        this.tourService = tourService;
        Helper.createDemo(tourService);
    }


    public void run () {
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

    private void mainMenu() {
        System.out.println();
        System.out.println(LINE + LOCALE.getString("menu") + LINE);
        System.out.println(LOCALE.getString("all.tours"));
        System.out.println(LOCALE.getString("tours.by.price"));
        System.out.println(LOCALE.getString("tours.by.duration"));
        System.out.println(LOCALE.getString("tours.by.type"));
        System.out.println(LOCALE.getString("exit"));
        int input = getInput();

        if (input < 0 || input > 4) {
            System.out.println(LOCALE.getString("wrong.input"));
           mainMenu();
        } else if (input == 0) {
            exit();
        } else {
            switch (input) {
                case 1:
                    allToursMenu();
                    break;
//                case 2:
//                    toursByPriceMenu();
//                    break;
//                case 3:
//                    toursByDurationMenu();
//                    break;
//                case 4:
//                    toursByTypeMenu();
//                    break;
                default:
                    break;

            }
        }
    }

    private void allToursMenu() {
        List<Tour> allTours = tourService.getAll();
        System.out.println();
        Helper.printHeader();
        allTours.forEach(System.out::println);
        chooseTourMenu();

    }

    private void chooseTourMenu() {
        System.out.println(LOCALE.getString("choose.tour"));
        long tourId = getLongInput();
        System.out.println(LOCALE.getString("book"));
        System.out.println(LOCALE.getString("back"));
        System.out.println(LOCALE.getString("exit"));
        int input = getInput();

        switch (input) {
            case 1:
                bookMenu(tourId);
                break;
            case 2:
                mainMenu();
                break;
            default:
                exit();
        }

    }

    private void bookMenu(long tourId) {
        System.out.println();
        Helper.printHeader();
        Tour tour = tourService.findById(tourId);
        System.out.println(tour);
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

        userService.register(User.init()
                .withName(data.get(0))
                .withSurname(data.get(1))
                .withEmail(data.get(2))
                .withPhone(data.get(3))
                .withPassword(data.get(4)).build());
        System.out.println(LOCALE.getString("user.registered"));
        loginMenu();
    }

    private void loginMenu() {
        System.out.println();
        System.out.println(LINE + LOCALE.getString("login.header") + LINE);
        System.out.print("Email: ");
        String email = SCAN.next();
        System.out.print(LOCALE.getString("password") + " ");
        String password = (SCAN.next());
        User user = userService.login(email, password);
        System.out.println(user);
        mainMenu();
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
