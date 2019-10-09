package com.yura.travel.validator;

import com.yura.travel.domain.user.User;
import com.yura.travel.exception.ArgumentIsNullException;
import com.yura.travel.exception.FieldNotCorrectException;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserValidatorExceptionTest {
    private static UserValidator userValidator;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void init() {
        userValidator = new UserValidator();
    }

    @Test
    public void shouldThrowArgumentIsNullException() {
        exception.expect(ArgumentIsNullException.class);
        exception.expectMessage("Argument must be not null");
        userValidator.validate(null);
    }

    @Test
    public void shouldThrowFieldNotCorrectExceptionForNotCorrectEmail() {
        User user = User.init().withName("Yura").withSurname("Petr")
                .withPhone("+380664003626").withPassword("123456")
                .withEmail("yuragmail.com").build();
        exception.expect(FieldNotCorrectException.class);
        exception.expectMessage("Email is not correct");
        userValidator.validate(user);
    }

    @Test
    public void shouldThrowFieldNotCorrectExceptionForNotCorrectName() {
        User user = User.init().withName("Y").withSurname("Petr")
                .withPhone("+380664003626").withPassword("123456")
                .withEmail("yura@gmail.com").build();
        exception.expect(FieldNotCorrectException.class);
        exception.expectMessage("Name is not correct");
        userValidator.validate(user);
    }

    @Test
    public void shouldThrowFieldNotCorrectExceptionForNotCorrectSurname() {
        User user = User.init().withName("Yura").withSurname("P")
                .withPhone("+380664003626").withPassword("123456")
                .withEmail("yura@gmail.com").build();
        exception.expect(FieldNotCorrectException.class);
        exception.expectMessage("Surname is not correct");
        userValidator.validate(user);
    }

    @Test
    public void shouldThrowFieldNotCorrectExceptionForNotCorrectPhone() {
        User user = User.init().withName("Yura").withSurname("Petr")
                .withPhone("003626").withPassword("123456")
                .withEmail("yura@gmail.com").build();
        exception.expect(FieldNotCorrectException.class);
        exception.expectMessage("Phone is not correct");
        userValidator.validate(user);
    }

    @Test
    public void shouldThrowFieldNotCorrectExceptionForNotCorrectPassword() {
        User user = User.init().withName("Yura").withSurname("Petr")
                .withPhone("+380664003626").withPassword("1")
                .withEmail("yura@gmail.com").build();
        exception.expect(FieldNotCorrectException.class);
        exception.expectMessage("Password is not correct");
        userValidator.validate(user);
    }
}
