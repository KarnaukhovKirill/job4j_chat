package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.model.Person;
import ru.job4j.model.Role;
import ru.job4j.repository.RoleRepository;
import ru.job4j.service.PersonService;
import ru.job4j.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController implements Controller<Person> {
    private final Service<Person> service;
    private final BCryptPasswordEncoder encoder;
    private final RoleRepository roleRepository;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);
    private final ObjectMapper objectMapper;

    public PersonController(final PersonService personService,
                            BCryptPasswordEncoder encoder,
                            RoleRepository roleRepository,
                            ObjectMapper objectMapper) {
        this.service = personService;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.objectMapper = objectMapper;
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
        if (person.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person is not found");
        } else {
            return new ResponseEntity<>(person.get(), HttpStatus.OK);
        }
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Person> save(@RequestBody Person person) {
        checkRequestPerson(person);
        return new ResponseEntity<>(
                this.service.save(person),
                HttpStatus.CREATED
        );
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        checkRequestPerson(person);
        this.service.save(person);
        return ResponseEntity.ok().build();
    }

    private void checkRequestPerson(Person person) {
        if (person.getUsername() == null || person.getPassword() == null) {
            throw new NullPointerException("Password of username is empty");
        }
        if (person.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be greater than 6 letters");
        }
        if (person.getRole() == null) {
            throw new IllegalArgumentException("Role not specified");
        }
        Role role = roleRepository.findByName(person.getRole().getName());
        if (role == null) {
            throw new NullPointerException("Role is not found");
        }
        person.setRole(role);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Person person = new Person();
        person.setId(id);
        if (service.getById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person is not found");
        }
        service.delete(person);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Person> signUp(@RequestBody Person person) {
        if (person.getUsername() == null || person.getPassword() == null) {
            throw new NullPointerException("Password of username is empty");
        }
        person.setPassword(encoder.encode(person.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        person.setRole(role);
        return new ResponseEntity<>(
                this.service.save(person),
                HttpStatus.CREATED
        );
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        logger.error(e.getLocalizedMessage());
    }
}
