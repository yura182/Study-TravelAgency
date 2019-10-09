package com.yura.travel;

import com.yura.travel.config.AppConfig;
import com.yura.travel.view.Menu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TourAgencyApp {
    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        appContext.getBean(Menu.class).run();
    }
}
