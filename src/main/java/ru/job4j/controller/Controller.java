package ru.job4j.controller;

import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;

public interface Controller<T> {
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T> getById(int id);
    ResponseEntity<T> save(@Valid T model);
    ResponseEntity<Void> update(@Valid T model);
    ResponseEntity<Void> delete(int id);
}
