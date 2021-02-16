package edu.asoldatov.library.service;

import edu.asoldatov.library.dto.IdDto;
import edu.asoldatov.library.dto.request.UserDtoRequest;
import edu.asoldatov.library.dto.response.UserDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.Role;
import edu.asoldatov.library.model.User;
import edu.asoldatov.library.repository.RoleRepository;
import edu.asoldatov.library.repository.UserRepository;
import edu.asoldatov.library.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TestUserService {

    private final static String USER_NAME = "UserName";
    private final static String PASSWORD = "password";
    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "lastName";
    private final static String PATRONYMIC = "patronymic";
    private final static String ROLE_USER = "ROLE_USER";
    private final static String ROLE_ADMIN = "ROLE_ADMIN";
    private final static String ENCODED_PASSWORD = "ENCODED PASSWORD";
    private final static String ANOTHER_PASSWORD = "ANOTHER_PASSWORD";
    private final static Integer YEAR_OF_BIRTH = 2000;
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;

    @Test
    public void testLoadUserByUsername() {
        UserService userService = new UserServiceImpl(bCryptPasswordEncoder, userRepository, roleRepository);

        User user = new User(1L, USER_NAME, PASSWORD, new HashSet<>(),
                FIRST_NAME, LAST_NAME, PATRONYMIC, YEAR_OF_BIRTH);

        when(userRepository.findByUsername(USER_NAME)).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername(USER_NAME);

        Assertions.assertAll(
                () -> Assertions.assertEquals(user.getUsername(), userDetails.getUsername()),
                () -> Assertions.assertEquals(user.getPassword(), userDetails.getPassword())
        );
    }

    @Test
    public void testLoadUserByUsernameFail() {
        UserService userService = new UserServiceImpl(bCryptPasswordEncoder, userRepository, roleRepository);

        when(userRepository.findByUsername(USER_NAME)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                UsernameNotFoundException.class, () -> userService.loadUserByUsername(USER_NAME)
        );
    }

    @Test
    public void testAllUsers() {
        UserService userService = new UserServiceImpl(bCryptPasswordEncoder, userRepository, roleRepository);

        List<User> users = new ArrayList<>();

        users.add(new User(1L, USER_NAME, PASSWORD, new HashSet<>(), FIRST_NAME,
                LAST_NAME, PATRONYMIC, YEAR_OF_BIRTH));

        users.add(new User(2L, USER_NAME, PASSWORD, new HashSet<>(), FIRST_NAME,
                LAST_NAME, PATRONYMIC, YEAR_OF_BIRTH));

        when(userRepository.findAll()).thenReturn(users);

        List<UserDtoResponse> userDtoResponses = userService.allUsers();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, userDtoResponses.size()),
                () -> verify(userRepository).findAll()
        );
    }

    @Test
    public void testRegisterUser() throws ServerException {
        UserService userService = new UserServiceImpl(bCryptPasswordEncoder, userRepository, roleRepository);

        Role role = new Role(1L, ROLE_USER);

        when(userRepository.findByUsername(USER_NAME)).thenReturn(Optional.empty());

        when(roleRepository.findByName(ROLE_USER)).thenReturn(Optional.of(role));

        when(bCryptPasswordEncoder.encode(PASSWORD)).thenReturn(ENCODED_PASSWORD);

        UserDtoRequest userDtoRequest = new UserDtoRequest(USER_NAME, PASSWORD, PASSWORD,
                FIRST_NAME, LAST_NAME, PATRONYMIC, YEAR_OF_BIRTH);

        List<Role> roles = Collections.singletonList(role);

        UserDtoResponse expectedResponse = new UserDtoResponse(0L, FIRST_NAME, LAST_NAME,
                PATRONYMIC, YEAR_OF_BIRTH, USER_NAME, roles);

        UserDtoResponse userDtoResponse = userService.registerUser(userDtoRequest);

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedResponse, userDtoResponse),
                () -> verify(userRepository).save(any()),
                () -> verify(userRepository).findByUsername(USER_NAME),
                () -> verify(bCryptPasswordEncoder).encode(PASSWORD)
        );
    }

    @Test
    public void testRegisterUserFail1() {
        UserService userService = new UserServiceImpl(bCryptPasswordEncoder, userRepository, roleRepository);

        User user = new User(1L, USER_NAME, PASSWORD, new HashSet<>(), FIRST_NAME, LAST_NAME, PATRONYMIC, YEAR_OF_BIRTH);

        when(userRepository.findByUsername(USER_NAME)).thenReturn(Optional.of(user));

        UserDtoRequest userDtoRequest = new UserDtoRequest(USER_NAME, PASSWORD, PASSWORD,
                FIRST_NAME, LAST_NAME, PATRONYMIC, YEAR_OF_BIRTH);

        Assertions.assertThrows(
                ServerException.class, () -> userService.registerUser(userDtoRequest)
        );
    }

    @Test
    public void testRegisterUserFail2() {
        UserService userService = new UserServiceImpl(bCryptPasswordEncoder, userRepository, roleRepository);

        Role role = new Role(1L, ROLE_USER);

        when(userRepository.findByUsername(USER_NAME)).thenReturn(Optional.empty());

        when(roleRepository.findByName(ROLE_USER)).thenReturn(Optional.of(role));

        when(bCryptPasswordEncoder.encode(PASSWORD)).thenReturn(ENCODED_PASSWORD);

        UserDtoRequest userDtoRequest = new UserDtoRequest(USER_NAME, PASSWORD, ANOTHER_PASSWORD,
                FIRST_NAME, LAST_NAME, PATRONYMIC, YEAR_OF_BIRTH);

        Assertions.assertThrows(
                ServerException.class, () -> userService.registerUser(userDtoRequest)
        );
    }

    @Test
    public void testAddAdminRole() throws ServerException {
        UserService userService = new UserServiceImpl(bCryptPasswordEncoder, userRepository, roleRepository);

        User user = new User(1L, USER_NAME, PASSWORD, new HashSet<>(), FIRST_NAME, LAST_NAME, PATRONYMIC, YEAR_OF_BIRTH);

        Role role = new Role(2L, ROLE_ADMIN);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        when(roleRepository.findByName(ROLE_ADMIN)).thenReturn(Optional.of(role));

        IdDto idDto = new IdDto(1L);

        userService.addAdminRole(idDto);

        Assertions.assertAll(
                () -> verify(userRepository).save(any()),
                () -> verify(userRepository).findById(1L)
        );
    }
}
