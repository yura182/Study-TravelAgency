package com.yura.travel.service;

import com.yura.travel.domain.user.User;

public interface UserService {

    User register(User user);

    User login(String email, String password);
}
