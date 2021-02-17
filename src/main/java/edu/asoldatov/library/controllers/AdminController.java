package edu.asoldatov.library.controllers;

import edu.asoldatov.library.dto.IdDto;
import edu.asoldatov.library.dto.response.UserDtoResponse;
import edu.asoldatov.library.exception.ServerException;
import edu.asoldatov.library.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        log.info("AdminController get all users");

        List<UserDtoResponse> users = userService.allUsers();

        model.addAttribute("addAdmin", new IdDto());

        model.addAttribute("users", users);

        return "admin";
    }

    @RequestMapping(path = "/admin", method = RequestMethod.POST)
    public String addAdminRole(Model model, @ModelAttribute("addUser") IdDto idDto) throws ServerException {
        log.info("AdminController add new admin");

        userService.addAdminRole(idDto);

        List<UserDtoResponse> users = userService.allUsers();

        model.addAttribute("addAdmin", new IdDto());

        model.addAttribute("users", users);

        return "admin";
    }
}
