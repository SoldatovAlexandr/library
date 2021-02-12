package edu.asoldatov.library.repository;

import edu.asoldatov.library.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
