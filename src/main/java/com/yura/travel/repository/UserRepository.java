package com.yura.travel.repository;

import com.yura.travel.domain.user.User;
import java.util.Optional;

public interface UserRepository extends Repository<User>{

    Optional<User> findByEmail(String email);
}
