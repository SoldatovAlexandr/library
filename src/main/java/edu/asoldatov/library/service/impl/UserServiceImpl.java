package edu.asoldatov.library.service.impl;

import edu.asoldatov.library.dto.IdDto;
import edu.asoldatov.library.dto.mapper.UserDtoMapper;
import edu.asoldatov.library.dto.request.UserDtoRequest;
import edu.asoldatov.library.dto.response.UserDtoResponse;
import edu.asoldatov.library.exception.ServerException;
import edu.asoldatov.library.model.Role;
import edu.asoldatov.library.model.User;
import edu.asoldatov.library.repository.RoleRepository;
import edu.asoldatov.library.repository.UserRepository;
import edu.asoldatov.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final static UserDtoMapper USER_DTO_MAPPER = UserDtoMapper.INSTANCE;
    private final static String USER_NOT_FOUND = "User not found";
    private final static String USER = "ROLE_USER";
    private final static String ADMIN = "ROLE_ADMIN";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }

    @Override
    public List<UserDtoResponse> allUsers() {
        Iterable<User> users = userRepository.findAll();

        return USER_DTO_MAPPER.toUserDtoResponses(users);
    }

    @Override
    public UserDtoResponse registerUser(UserDtoRequest userDtoRequest) throws ServerException {
        User user = USER_DTO_MAPPER.toUser(userDtoRequest);

        if (hasUsername(user.getUsername())) {
            throw new ServerException("taken.username");
        }

        setRoles(user);

        user.setPassword(bCryptPasswordEncoder.encode(userDtoRequest.getPassword()));

        userRepository.save(user);

        return USER_DTO_MAPPER.toUserDtoResponse(user);
    }

    @Override
    public void addAdminRole(IdDto idDto) throws ServerException {
        long userId = idDto.getId();

        User user = userRepository.findById(userId).orElseThrow(() -> new ServerException("wrong.user.id"));

        Role role = getRoleByName(ADMIN);

        user.getRoles().add(role);

        userRepository.save(user);
    }

    private void setRoles(User user) throws ServerException {
        Set<Role> userRoles = new HashSet<>();

        Role role = getRoleByName(USER);

        userRoles.add(role);

        user.setRoles(userRoles);
    }

    private Role getRoleByName(String roleName) throws ServerException {
        return roleRepository.findByName(roleName).orElseThrow(() -> new ServerException("empty.database"));
    }

    private boolean hasUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
