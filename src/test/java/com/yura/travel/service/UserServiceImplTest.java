package com.yura.travel.service;

import com.yura.travel.domain.user.User;
import com.yura.travel.encoder.PasswordEncoder;
import com.yura.travel.exception.ArgumentIsNullException;
import com.yura.travel.exception.FieldNotCorrectException;
import com.yura.travel.exception.NoSuchUserException;
import com.yura.travel.exception.PasswordNotMatchException;
import com.yura.travel.repository.UserRepository;
import com.yura.travel.validator.UserValidator;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;

import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private UserRepository repository;

    @Mock
    private UserValidator userValidator;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @After
    public void resetMocks() {
        reset(repository);
        reset(userValidator);
        reset(passwordEncoder);
    }

    @Test
    public void shouldRegisterUser() {
        User expected = User.init().withName("Yura").withSurname("Petr")
                .withPhone("+380664003626").withPassword("123456")
                .withEmail("yura@gmail.com").build();
        when(repository.save(any(User.class))).thenReturn(expected);
        User actual = userService.register(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowArgumentIsNullException() {
        exception.expect(ArgumentIsNullException.class);

        doThrow(ArgumentIsNullException.class).when(userValidator).validate(null);
        userService.register(null);
    }

    @Test
    public void shouldThrowFieldNotCorrectException() {
        User user = User.init().withName("Yura").withSurname("Petr")
                .withPhone("+380664003626").withPassword("123456")
                .withEmail("yura@gmail.com").build();
        exception.expect(FieldNotCorrectException.class);

        doThrow(FieldNotCorrectException.class).when(userValidator).validate(any(User.class));
        userService.register(user);
    }

    @Test
    public void shouldLoginUser() {
        User expected = User.init().withName("Yura").withSurname("Petr")
                .withPhone("+380664003626").withPassword("123456")
                .withEmail("yura@gmail.com").build();
        when(repository.findByEmail(anyString())).thenReturn(Optional.ofNullable(expected));
        when(passwordEncoder.verifyPassword(anyString(), anyString())).thenReturn(true);

        User actual = userService.login("yura@gmail.com", "123456");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowNoSuchUserException() {
        exception.expect(NoSuchUserException.class);
        exception.expectMessage("User not found");
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        userService.login("yura182@gmail.com", "123456");
    }

    @Test
    public void shouldThrowPasswordNotMatchException() {
        User user = User.init().withName("Yura").withSurname("Petr")
                .withPhone("+380664003626").withPassword("123456")
                .withEmail("yura@gmail.com").build();
        exception.expect(PasswordNotMatchException.class);
        exception.expectMessage("Password is incorrect");
        when(repository.findByEmail("yura@gmail.com")).thenReturn(Optional.ofNullable(user));
        when(passwordEncoder.verifyPassword(anyString(),anyString())).thenReturn(false);

        userService.login("yura@gmail.com", "123456");
    }
}