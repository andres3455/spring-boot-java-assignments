package org.adaschool.api.service.user;

import org.adaschool.api.repository.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service   // 👈 Esto hace que Spring registre el bean
public class UsersServiceImpl implements UsersService {

    private final List<User> users = new ArrayList<>();


    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public List<User> all() {
        return List.of();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(String id) {

    }
}
