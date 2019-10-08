package com.yura.travel.view;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LocaleManager {
    LOCALE;
    private final String resourceName = "menu";
    private ResourceBundle messages;

    LocaleManager() {
        messages = ResourceBundle.getBundle(resourceName, Locale.getDefault());
    }

    public void setLocale(String key) {
        Locale locale = new Locale(key);
        messages = ResourceBundle.getBundle(resourceName, locale);
    }

    public String getString(String key) {
        return messages.getString(key);
    }
}
