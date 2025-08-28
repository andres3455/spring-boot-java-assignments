package org.adaschool.api.service.user;

import org.adaschool.api.repository.user.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    User save(User user);

    List<User> all();

    Optional<User> findById(String id);

    void deleteById(String id);
}
