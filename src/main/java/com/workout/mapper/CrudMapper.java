package com.workout.mapper;

import java.util.List;
import java.util.Optional;

public interface CrudMapper<T> {

    void save(T model);

    List<T> findAll();

    Optional<T> findById(Long id);

    void delete(Long id);
}
