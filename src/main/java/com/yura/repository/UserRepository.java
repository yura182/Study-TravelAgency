package com.yura.repository;

import com.yura.domain.user.User;
import java.util.Optional;

public interface UserRepository {
    User save(User student);

    Optional<User> findById(Long id);

    Optional<User> update(User student);

    Optional<User> deleteById(Long id);

    Optional<User> findByEmail(String email);
}
