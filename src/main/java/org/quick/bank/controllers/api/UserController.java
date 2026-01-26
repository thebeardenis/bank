package org.quick.bank.controllers.api;

import jakarta.transaction.Transactional;
import org.quick.bank.models.CardDTO;
import org.quick.bank.models.Transaction;
import org.quick.bank.models.User;
import org.quick.bank.models.UserDTO;
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
    public ResponseEntity<String> createUser(@RequestBody UserDTO dto) {
        userService.create(dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User with name: " + dto.getName() + ", created and saved on DB.");
    }

    @PostMapping("/delete_user")
    public ResponseEntity<String> deleteById(@RequestBody UserDTO dto) {
        userService.deleteUserById(dto.getId());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("User with id: " + dto.getId() + ", deleted.");
    }

    @PostMapping("/get_all_users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    @PostMapping("/get_user_by_id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userService.getUserById(id));
    }
}
