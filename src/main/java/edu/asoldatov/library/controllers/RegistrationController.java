package edu.asoldatov.library.controllers;

import edu.asoldatov.library.dto.request.UserDtoRequest;
import edu.asoldatov.library.exception.ServerException;
import edu.asoldatov.library.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String registration(Model model) {
        log.info("RegistrationController get registration page");

        model.addAttribute("user", new UserDtoRequest());

        return "registration";
    }

    @PostMapping
    public String registerUser(
            @ModelAttribute(name = "user") @Valid UserDtoRequest userDtoRequest,
            BindingResult bindingResult) throws ServerException {
        log.info("RegistrationController register new user");

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.registerUser(userDtoRequest);

        return "redirect:/";
    }
}
