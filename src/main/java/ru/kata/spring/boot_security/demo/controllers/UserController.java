package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showUser(Model model, Principal principal) {
        User user = userService.findByName(principal.getName());
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        model.addAttribute("user", user);
        return "user";
    }
}
