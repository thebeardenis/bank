package org.quick.bank.services;

import lombok.extern.slf4j.Slf4j;
import org.quick.bank.exceptions.*;
import org.quick.bank.models.*;
import org.quick.bank.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void create(UserDTO dto) {
        if (dto.getId() == null) {
            if (dto.necessaryFieldIsNull()) {
                throw new InputDataException("Necessary values of UserDTO is null.");
            } else {
                var user = new User();
                user.setPassword(dto.getPassword());
                user.setEmail(dto.getEmail());
                user.setName(dto.getName());
                if (dto.notNecessaryFieldsIsNotNull()) {
                    if (dto.getCards() != null) {
                        user.setCards(dto.getCards());
                    }
                    if (dto.getTransactionsFrom() != null) {
                        user.setTransactionsFrom(dto.getTransactionsFrom());
                    }
                    if (dto.getTransactionsTo() != null) {
                        user.setTransactionsTo(dto.getTransactionsTo());
                    }
                }
                log.info("Create user: {}", user);
                save(user);
            }
        } else {
            throw new UserAlreadyExistException("User with id :" + dto.getId() + ", Already existed");
        }
    }

    public void save(User user) {
        userRepository.save(user);
        log.info("Saved user: {}", user);

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
