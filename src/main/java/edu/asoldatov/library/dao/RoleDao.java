package edu.asoldatov.library.dao;

import edu.asoldatov.library.model.Role;

import java.util.Optional;

public interface RoleDao {
    Optional<Role> findByName(String name);
}
