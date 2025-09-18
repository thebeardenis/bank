package org.quick.bank.controllers;

import org.quick.bank.models.CardDTO;
import org.quick.bank.models.User;
import org.quick.bank.models.UserDTO;
import org.quick.bank.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> index(@RequestBody UserDTO dto) {
        userService.create(dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User with name: " + dto.getName() + ", created and saved on DB.");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteById(@RequestBody UserDTO dto) {
        userService.deleteUserById(dto.getId());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("User with id: " + dto.getId() + ", deleted.");
    }

    @PostMapping("/getAll")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);

    }

    @PostMapping("/addBankCard/{id}")
    public ResponseEntity<String> addBankCard(@PathVariable("id") Long id, @RequestBody CardDTO card) {
        userService.addCardById(card, id);
        return  ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Card: " + card.getName() + ", added to user.");
    }
}
