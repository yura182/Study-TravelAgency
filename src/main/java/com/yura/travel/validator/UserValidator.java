package com.yura.travel.validator;

import com.yura.travel.domain.user.User;
import com.yura.travel.exception.ArgumentIsNullException;
import com.yura.travel.exception.FieldNotCorrectException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator {
    private static final Logger LOGGER = Logger.getLogger("file");
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String NAME_PATTERN = "[A-Za-z]{2,10}";
    private static final String SURNAME_PATTERN = "[A-Za-z]{2,20}";
    private static final String PASSWORD_PATTERN = "[A-Za-z0-9]{6,}";
    private static final String PHONE_PATTERN = "^\\+?3?8?(0\\d{9})$";

    public boolean validate(User user) {
        isNull("validate", user);
        checkEmail(user.getEmail());
        checkName(user.getName());
        checkSurname(user.getSurname());
        checkPhone(user.getPhone());
        checkPassword(user.getPassword());
        return true;
    }

    private void checkEmail(String email) {
        isNull("checkEmail", email);
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            LOGGER.info("Email is not correct");
            throw new FieldNotCorrectException("Email is not correct");
        }
    }

    private void checkName(String name) {
        isNull("checkName", name);
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            LOGGER.info("Name is not correct");
            throw new FieldNotCorrectException("Name is not correct");
        }
    }

    private void checkSurname(String surname) {
        isNull("checkSurname", surname);
        Pattern pattern = Pattern.compile(SURNAME_PATTERN);
        Matcher matcher = pattern.matcher(surname);
        if (!matcher.matches()) {
            LOGGER.info("Surname is not correct");
            throw new FieldNotCorrectException("Surname is not correct");
        }
    }

    private void checkPhone(String phone) {
        isNull("checkPhone", phone);
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            LOGGER.info("Phone is not correct");
            throw new FieldNotCorrectException("Phone is not correct");
        }
    }

    private void checkPassword(String password) {
        isNull("checkPassword", password);
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            LOGGER.info("Password is not correct");
            throw new FieldNotCorrectException("Password is not correct");
        }
    }

    public boolean isNull(String message, Object... objects) {
        for (Object obj : objects) {
            if (Objects.isNull(obj)) {
                LOGGER.info("Argument is null in method" + message);
                throw new ArgumentIsNullException("Argument must be not null");
            }
        }
        return true;
    }
}
