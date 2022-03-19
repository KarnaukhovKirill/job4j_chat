package ru.job4j.service;

import java.util.List;
import java.util.Optional;

public interface Service<T> {
    List<T> getAll();
    Optional<T> getById(int id);
    T save(T model);
    void delete(T model);
}
