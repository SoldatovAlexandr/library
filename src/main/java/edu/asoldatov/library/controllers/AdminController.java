package edu.asoldatov.library.controllers;

import edu.asoldatov.library.dto.IdDto;
import edu.asoldatov.library.dto.response.UserDtoResponse;
import edu.asoldatov.library.exception.ServerException;
import edu.asoldatov.library.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        log.info("AdminController get all users");

        List<UserDtoResponse> users = userService.allUsers();

        model.addAttribute("addAdmin", new IdDto());

        model.addAttribute("users", users);

        return "admin";
    }

    @PostMapping
    public String addAdminRole(Model model, @ModelAttribute("addUser") IdDto idDto) throws ServerException {
        log.info("AdminController add new admin");

        userService.addAdminRole(idDto);

        List<UserDtoResponse> users = userService.allUsers();

        model.addAttribute("addAdmin", new IdDto());

        model.addAttribute("users", users);

        return "admin";
    }
}
