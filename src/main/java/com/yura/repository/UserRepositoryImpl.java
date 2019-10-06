package com.yura.repository;

import com.yura.domain.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private Map<Long, User> idToUser = new HashMap<>();

    @Override
    public User save(User user) {
        return idToUser.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(idToUser.get(id));
    }

    @Override
    public Optional<User> update(User student) {
        return Optional.ofNullable(idToUser.replace(student.getId(), student));
    }

    @Override
    public Optional<User> deleteById(Long id) {
        return Optional.ofNullable(idToUser.remove(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return idToUser.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny();
    }
}
