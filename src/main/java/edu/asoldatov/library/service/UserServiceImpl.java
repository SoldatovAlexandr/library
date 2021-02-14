package edu.asoldatov.library.service;

import edu.asoldatov.library.dao.*;
import edu.asoldatov.library.dto.mapper.UserDtoMapper;
import edu.asoldatov.library.dto.request.AddAdminDtoRequest;
import edu.asoldatov.library.dto.request.RegisterUserDtoRequest;
import edu.asoldatov.library.dto.response.UserDtoResponse;
import edu.asoldatov.library.erroritem.code.ServerErrorCodeWithField;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.Role;
import edu.asoldatov.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl extends ServiceBase implements UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final static String USER = "ROLE_USER";

    private final static String ADMIN = "ROLE_ADMIN";

    @Autowired
    protected UserServiceImpl(
            BookDao bookDao,
            GenreDao genreDao,
            UserDao userDao,
            AuthorDao authorDao,
            RoleDao roleDao,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        super(bookDao, genreDao, userDao, authorDao, roleDao);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByName(username);
    }


    @Override
    public List<UserDtoResponse> allUsers(Model model) {
        Iterable<User> users = userDao.getAllUsers();

        return UserDtoMapper.INSTANCE.toUserDtoResponses(users);
    }


    @Override
    public UserDtoResponse registerUser(RegisterUserDtoRequest request,
                                        BindingResult bindingResult, Model model) throws ServerException {

        String username = request.getUsername();

        if (hasUsername(username)) {
            throw new ServerException(ServerErrorCodeWithField.WRONG_USERNAME);
        }

        if (!equalsPassword(request)) {
            throw new ServerException(ServerErrorCodeWithField.PASSWORDS_NOT_EQUALS);
        }

        User user = UserDtoMapper.INSTANCE.toUser(request);

        setRoles(user);

        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        userDao.insert(user);

        return UserDtoMapper.INSTANCE.toUserDtoResponse(user);
    }

    @Override
    public void addAdminRole(AddAdminDtoRequest request) throws ServerException {
        long userId = request.getUserId();

        User user = getUserById(userId);

        Role role = getRoleByName(ADMIN);

        user.getRoles().add(role);

        userDao.update(user);
    }


    private void setRoles(User user) throws ServerException {
        Set<Role> userRoles = new HashSet<>();

        Role role = getRoleByName(USER);

        userRoles.add(role);

        user.setRoles(userRoles);
    }

    private boolean equalsPassword(RegisterUserDtoRequest request) {
        return request.getPassword().equals(request.getPasswordConfirm());
    }
}
