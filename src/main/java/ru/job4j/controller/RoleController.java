package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.model.Role;
import ru.job4j.service.RoleService;
import ru.job4j.service.Service;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController implements Controller<Role> {
    private final Service<Role> service;

    public RoleController(final RoleService roleService) {
        this.service = roleService;
    }

    @Override
    @GetMapping("/")
    public List<Role> getAll() {
        return service.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable int id) {
        var role = service.getById(id);
        if (role.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found");
        } else {
            return new ResponseEntity<>(role.get(), HttpStatus.OK);
        }
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Role> save(@RequestBody Role role) {
        if (role.getName() == null) {
            throw new NullPointerException("Role's name is empty");
        }
        return new ResponseEntity<>(
                this.service.save(role),
                HttpStatus.CREATED
        );
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Role role) {
        if (role.getName() == null) {
            throw new NullPointerException("Role's name is empty");
        }
        this.service.save(role);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Role role = new Role();
        role.setId(id);
        if (service.getById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found");
        }
        service.delete(role);
        return ResponseEntity.ok().build();
    }
}
