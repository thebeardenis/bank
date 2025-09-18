package org.quick.bank.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.quick.bank.exceptions.UserNotSavedException;
import org.quick.bank.models.BankCard;
import org.quick.bank.models.CardDTO;
import org.quick.bank.models.User;
import org.quick.bank.models.UserDTO;
import org.quick.bank.repositories.BankCardRepository;
import org.quick.bank.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public void create(UserDTO dto) {
        var user = new User();
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getName() != null) {
            user.setName(dto.getName());
        }
        if (dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }
        save(user);
        log.info("Create user: {}", user);

    }

    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
        log.info("Deleted user with id: {}", id);
    }

    public void save(User user) {
        if (user != null) {
            userRepository.save(user);
            log.info("Saved user: {}", user);
        } else {
            throw new UserNotSavedException("User is null");
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void addCardById(CardDTO cardDTO, Long id) {
        User user = userRepository.getReferenceById(id);
        var card = new BankCard();
        if (cardDTO.getBalance() != null) {
            card.setBalance(cardDTO.getBalance());
        } else {
            card.setBalance(BigDecimal.ZERO);
        }
        card.setName(cardDTO.getName());
        user.addCard(card);
        userRepository.save(user);
        log.info("Card {}, with balance {}, added to user {}", card.getName(), card.getBalance(), user);
    }

}
