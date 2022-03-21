package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Person;
import ru.job4j.model.Role;
import ru.job4j.repository.RoleRepository;
import ru.job4j.service.PersonService;
import ru.job4j.service.Service;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController implements Controller<Person> {
    private final Service<Person> service;
    private final BCryptPasswordEncoder encoder;
    private final RoleRepository roleRepository;

    public PersonController(final PersonService personService,
                            BCryptPasswordEncoder encoder,
                            RoleRepository roleRepository) {
        this.service = personService;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @GetMapping("/")
    public List<Person> getAll() {
        return service.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable int id) {
        var person = service.getById(id);
        return new ResponseEntity<>(
                person.orElse(new Person()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return new ResponseEntity<>(
                this.service.save(person),
                HttpStatus.CREATED
        );
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        this.service.save(person);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Person person = new Person();
        person.setId(id);
        service.delete(person);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Person> signUp(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        person.setRole(role);
        return new ResponseEntity<>(
                this.service.save(person),
                HttpStatus.CREATED
        );
    }
}
