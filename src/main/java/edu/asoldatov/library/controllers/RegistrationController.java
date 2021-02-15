package edu.asoldatov.library.controllers;

import edu.asoldatov.library.dto.request.RegisterUserDtoRequest;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/registration")
    public String registration(Model model) {
        LOGGER.info("RegistrationController get registration page");

        model.addAttribute("user", new RegisterUserDtoRequest());

        return "registration";
    }

    @PostMapping(path = "/registration")
    public String registerUser(
            @ModelAttribute(name = "user") @Valid RegisterUserDtoRequest request,
            BindingResult bindingResult,
            Model model) throws ServerException {
        LOGGER.info("RegistrationController register new user");

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.registerUser(request, bindingResult, model);

        return "redirect:/";
    }
}
