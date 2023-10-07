package ru.venikov.spring.boot_security.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.venikov.spring.boot_security.models.User;
import ru.venikov.spring.boot_security.security.OurUserDetails;
import ru.venikov.spring.boot_security.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UsersController {

    private final UserService userService;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<User> userPage(Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OurUserDetails userDetails = (OurUserDetails) authentication.getPrincipal();

        return new ResponseEntity<>(userService.getUser(userDetails.getUser().getId()),HttpStatus.OK);
    }
}
