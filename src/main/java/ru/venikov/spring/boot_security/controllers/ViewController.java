package ru.venikov.spring.boot_security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.venikov.spring.boot_security.models.User;

@Controller
public class ViewController {
    @GetMapping("/admin")
    public String showAllUser(Model model) {
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @GetMapping("/user")
    public String showOneUser() {
        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "loginPage";
    }
}
