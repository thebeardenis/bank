package org.quick.bank.services;

import lombok.extern.slf4j.Slf4j;
import org.quick.bank.entity.models.User;
import org.quick.bank.exceptions.*;
import org.quick.bank.entity.requests.CreateUserRequest;
import org.quick.bank.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User create(CreateUserRequest request) {
        User user = new User();
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserEmailAlreadyUse("email already use");
        } else {
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            userRepository.save(user);
        }
        return user;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
        log.info("Deleted user with id: {}", id);
    }

    public User getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
