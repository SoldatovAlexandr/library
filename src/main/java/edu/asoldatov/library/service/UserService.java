package edu.asoldatov.library.service;

import edu.asoldatov.library.dto.request.IdDto;
import edu.asoldatov.library.dto.request.UserDtoRequest;
import edu.asoldatov.library.dto.response.UserDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDtoResponse> allUsers(Model model);

    UserDtoResponse registerUser(UserDtoRequest request, BindingResult bindingResult, Model model) throws ServerException;

    void addAdminRole(IdDto adminDto) throws ServerException;
}
