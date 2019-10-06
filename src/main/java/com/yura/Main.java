package com.yura;

import com.yura.domain.user.User;
import com.yura.repository.UserRepository;
import com.yura.repository.UserRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        User user = User.init().withName("Yura").withSurname("Petrashenko")
                .withEmail("yuras182@yandex.ru")
                .withPhone("066-400-36-26")
                .withPassword("11111").build();
        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.save(user);
        System.out.println(userRepository.findByEmail("yuras182@yandex.ru"));
    }


}
