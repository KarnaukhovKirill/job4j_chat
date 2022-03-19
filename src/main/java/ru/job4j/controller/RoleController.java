package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        var message = service.getById(id);
        return new ResponseEntity<>(
                message.orElse(new Role()),
                message.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Role> save(@RequestBody Role role) {
        return new ResponseEntity<>(
                this.service.save(role),
                HttpStatus.CREATED
        );
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Role role) {
        this.service.save(role);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Role role = new Role();
        role.setId(id);
        service.delete(role);
        return ResponseEntity.ok().build();
    }
}
