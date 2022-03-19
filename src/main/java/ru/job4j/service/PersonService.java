package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.model.Person;
import ru.job4j.repository.PersonRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Service
public class PersonService implements Service<Person> {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAll() {
        return StreamSupport.stream(
                        personRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Person> getById(int id) {
        return personRepository.findById(id);
    }

    @Override
    public Person save(Person model) {
        return personRepository.save(model);
    }

    @Override
    public void delete(Person model) {
        personRepository.delete(model);
    }
}
