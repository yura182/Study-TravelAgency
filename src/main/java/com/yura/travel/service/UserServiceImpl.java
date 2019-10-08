package com.yura.travel.service;

import com.yura.travel.domain.user.User;
import com.yura.travel.encoder.PasswordEncoder;
import com.yura.travel.exception.NoSuchUserException;
import com.yura.travel.exception.PasswordNotMatchException;
import com.yura.travel.repository.UserRepository;
import com.yura.travel.validator.UserValidator;
import org.apache.log4j.Logger;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger("file");
    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserValidator userValidator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        userValidator.validate(user);

        String encodePassword = passwordEncoder.encode(user.getPassword());
        User userWithEncodedPassword = new User(user, encodePassword);
        LOGGER.info("User registered");
        return userRepository.save(userWithEncodedPassword);
    }

    @Override
    public User login(String email, String password) {
        userValidator.isNull("Login", email, password);
        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            LOGGER.info("User not found");
            throw new NoSuchUserException("User not found");
        }

        boolean passwordMatch = passwordEncoder.verifyPassword(password, user.get().getPassword());

        if (!passwordMatch) {
            LOGGER.info("Incorrect Password");
            throw new PasswordNotMatchException("Password is incorrect");
        }

        LOGGER.info("User logged in");
        return user.get();

    }
}
