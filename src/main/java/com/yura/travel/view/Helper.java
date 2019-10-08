package com.yura.travel.view;


import com.yura.travel.domain.tour.*;
import com.yura.travel.service.TourService;

public class Helper {
    private static final LocaleManager LOCALE = LocaleManager.LOCALE;

    public static void createDemo(TourService tourService) {
        tourService.add(Tour.init().withName("Spa tour").withType(TourType.HEALTH)
                .withSpecification(new TourSpecification(Food.FB, Transport.BUS, 5))
                .withPrice(500).build());
    }


    public static void printHeader() {
        StringBuilder out = new StringBuilder();
        out.append(String.format("| %-2s |", LOCALE.getString("id")));
        out.append(String.format(" %-15s|", LOCALE.getString("tour")));
        out.append(String.format(" %-15s|", LOCALE.getString("type")));
        out.append(String.format(" %-25s|", LOCALE.getString("specification")));
        out.append(String.format(" %-6s|", LOCALE.getString("price")));

        System.out.println(out.toString());
    }
}
