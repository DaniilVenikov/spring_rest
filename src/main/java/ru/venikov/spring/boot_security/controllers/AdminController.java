package ru.venikov.spring.boot_security.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.venikov.spring.boot_security.dto.UserDTO;
import ru.venikov.spring.boot_security.exceptions.UserNotCreatedException;
import ru.venikov.spring.boot_security.exceptions.UserNotUpdatedException;
import ru.venikov.spring.boot_security.models.Role;
import ru.venikov.spring.boot_security.models.User;
import ru.venikov.spring.boot_security.repositories.RoleRepository;
import ru.venikov.spring.boot_security.services.RegistrationService;
import ru.venikov.spring.boot_security.services.UserService;
import ru.venikov.spring.boot_security.util.UserValidator;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final RegistrationService registrationService;

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public AdminController(UserService userService, UserValidator userValidator, RegistrationService registrationService, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.registrationService = registrationService;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roles = roleRepository.getRolesBy().stream().toList();
        return !roles.isEmpty()
                ? new ResponseEntity<>(roles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return new ResponseEntity<>(userService.getUsers().stream().map(this::convertToUserDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") long id) {
        return new ResponseEntity<>(convertToUserDTO(userService.getUser(id)), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Validated  UserDTO userDTO, BindingResult bindingResult) {
        User user = converToUser(userDTO);
        userValidator.validate(user, bindingResult);

        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";")
                        .append("\n");
            }

            throw new UserNotCreatedException(errorMsg.toString());
        }


        registrationService.register(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody @Validated UserDTO userDTO, BindingResult bindingResult,
                                                 @PathVariable("id") long id) {
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";")
                        .append("\n");
            }

            throw new UserNotUpdatedException(errorMsg.toString());
        }

        userService.update(id, converToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private User converToUser(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    private UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
