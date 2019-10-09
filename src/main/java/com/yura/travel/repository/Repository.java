package com.yura.travel.repository;

import java.util.Optional;

public interface Repository<T> {
    T save(T object);

    Optional<T> findById(Long id);

    Optional<T> update(T object);

    Optional<T> deleteById(Long id);
}
