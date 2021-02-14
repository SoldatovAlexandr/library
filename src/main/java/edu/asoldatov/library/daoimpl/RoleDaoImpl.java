package edu.asoldatov.library.daoimpl;

import edu.asoldatov.library.dao.RoleDao;
import edu.asoldatov.library.model.Role;
import edu.asoldatov.library.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleDaoImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
