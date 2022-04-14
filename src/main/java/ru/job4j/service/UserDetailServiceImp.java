package ru.job4j.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.model.Person;
import org.springframework.security.core.userdetails.User;
import ru.job4j.repository.PersonRepository;
import static java.util.Collections.emptyList;

@Service
public class UserDetailServiceImp implements UserDetailsService {
    private final PersonRepository users;

    public UserDetailServiceImp(PersonRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getUsername(), user.getPassword(), emptyList());
    }

}
