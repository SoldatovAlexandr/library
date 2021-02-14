package edu.asoldatov.library.dao;

import edu.asoldatov.library.model.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> getById(Long userId);

    Optional<User> getByUsername(String username);

    void insert(User user);

    Iterable<User> getAllUsers();

    void update(User user);
}
