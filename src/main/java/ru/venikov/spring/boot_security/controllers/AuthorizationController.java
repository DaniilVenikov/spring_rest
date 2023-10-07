package ru.venikov.spring.boot_security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class AuthorizationController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "loginPage";
    }
    @PostMapping("/login")
    public String processLogin() {
        return "redirect:/";
    }
}
