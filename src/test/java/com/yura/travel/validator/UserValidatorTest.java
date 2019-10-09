package com.yura.travel.validator;

import com.yura.travel.domain.user.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class UserValidatorTest {
    private User user;
    private UserValidator userValidator;

    @Before
    public void init() {
        userValidator = new UserValidator();
        user = User.init().withName("Yura").withSurname("Petr")
                .withPhone("+380664003626").withPassword("123456")
                .withEmail("yura@gmail.com").build();
    }

    @Test
    public void shouldReturnTrue() {
        boolean actual = userValidator.validate(user);
        assertTrue(actual);
    }

    @Test
    public void shouldReturnTrueForNonNull() {
        boolean actual = userValidator.isNull("test", user);
        assertTrue(actual);
    }
}