package org.quick.bank.controllers.api;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = userService.create(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

    @PostMapping("/delete_user_by_id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Success.");
    }

    @PostMapping("/get_user_by_id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userService.getUserById(id));
    }

    @PostMapping("/get_all_users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }
}
