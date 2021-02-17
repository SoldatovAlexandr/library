package edu.asoldatov.library.service;

import edu.asoldatov.library.dto.IdDto;
import edu.asoldatov.library.dto.request.UserDtoRequest;
import edu.asoldatov.library.dto.response.UserDtoResponse;
import edu.asoldatov.library.exception.ServerException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDtoResponse> allUsers();

    UserDtoResponse registerUser(UserDtoRequest request) throws ServerException;

    void addAdminRole(IdDto adminDto) throws ServerException;
}
