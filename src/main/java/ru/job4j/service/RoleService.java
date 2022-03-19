package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.model.Role;
import ru.job4j.repository.RoleRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Service
public class RoleService implements Service<Role> {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return StreamSupport.stream(
                        roleRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Role> getById(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role model) {
        return roleRepository.save(model);
    }

    @Override
    public void delete(Role model) {
        roleRepository.delete(model);
    }
}
