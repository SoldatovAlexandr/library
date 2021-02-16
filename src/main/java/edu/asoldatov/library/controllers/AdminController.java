package edu.asoldatov.library.controllers;

import edu.asoldatov.library.dto.IdDto;
import edu.asoldatov.library.dto.response.UserDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        LOGGER.info("AdminController get all users");

        List<UserDtoResponse> users = userService.allUsers(model);

        model.addAttribute("addAdmin", new IdDto());

        model.addAttribute("users", users);

        return "admin";
    }

    @PostMapping("/admin")
    public String addAdminRole(Model model, @ModelAttribute("addUser") IdDto idDto) throws ServerException {
        LOGGER.info("AdminController add new admin");

        userService.addAdminRole(idDto);

        List<UserDtoResponse> users = userService.allUsers(model);

        model.addAttribute("addAdmin", new IdDto());

        model.addAttribute("users", users);

        return "admin";
    }
}
