package ru.venikov.spring.boot_security.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.venikov.spring.boot_security.models.User;

@RestController
@RequestMapping("/userApi")
public class UsersController {

    @GetMapping()
    public ResponseEntity<User> userPage(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }
}
