package org.quick.bank.controllers.api;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.quick.bank.entity.DTOs.UserDTO;
import org.quick.bank.entity.models.User;
import org.quick.bank.entity.requests.CreateUserRequest;
import org.quick.bank.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Transactional
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/create_user")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = userService.create(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new UserDTO(user));
    }

    @PostMapping("/delete_user_by_id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Success.");
    }

    @PostMapping("/get_user_by_id/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new UserDTO(userService.getUserById(id)));
    }

    @PostMapping("/get_all_users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> usersDTOs = userService.getAllUsers().stream()
                .map(UserDTO::new)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usersDTOs);
    }
}
