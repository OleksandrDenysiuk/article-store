package com.example.articlestore.controller;


import com.example.articlestore.domain.User;
import com.example.articlestore.service.UserService;
import com.example.articlestore.validator.UserRegisterValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


/*
The controller is responsible for user authentication.
 */
@Slf4j
@Controller
public class AuthenticateController {

    private final UserService userService;

    private final UserRegisterValidator userValidator;

    public AuthenticateController(UserService userService, UserRegisterValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    //method show registration form
    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        model.addAttribute("userForm",new User());
        return "authenticate/registration/form";
    }

    //method validate form and create new user. Returns main page.
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "authenticate/registration/form";
        }

        userService.save(userForm);

        return "redirect:/";
    }
}
