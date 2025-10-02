package org.quick.bank.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.quick.bank.exceptions.*;
import org.quick.bank.models.*;
import org.quick.bank.repositories.BankCardRepository;
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


    @Transactional
    public void create(UserDTO dto) {
        if (dto.getId() == null) {
            if (dto.someoneValuesIsNull()) {
                throw new InputDataException("Someone values of UserDTO is null.");
            } else {
                var user = new User();
                user.setPassword(dto.getPassword());
                user.setEmail(dto.getEmail());
                user.setName(dto.getName());
                log.info("Create user: {}", user);
                save(user);
            }
        } else {
            throw new UserAlreadyExistException("User with id :" + dto.getId() + ", Already existed");
        }
    }

    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
        log.info("Deleted user with id: {}", id);
    }

    @Transactional
    public void save(User user) {
        if (user != null) {
            userRepository.save(user);
            log.info("Saved user: {}", user);
        } else {
            throw new InputDataException("User is null");
        }
    }

    @Transactional
    public void update(User user) {
        if (user != null) {
            userRepository.save(user);
            log.info("Update user: {}", user);
        } else {
            throw new InputDataException("User is null");
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<Transaction> getAllTransactionsById(Long id) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(getFromTransactionsById(id));
        transactions.addAll(getToTransactionsById(id));
        transactions.sort(Comparator.comparing(Transaction::getDealTime));
        return transactions;
    }

    @Transactional
    public void addCardById(CardDTO cardDTO, Long user_id) {
        User user = userRepository.getReferenceById(user_id);
        if (!cardDTO.someoneValuesIsNull()) {
            var card = new BankCard();
            card.setBalance(cardDTO.getBalance() == null ? BigDecimal.ZERO : cardDTO.getBalance());
            card.setName(cardDTO.getName());
            user.addCard(card);
            update(user);
            log.info("Card {}, with balance {}, added to user {}", card.getName(), card.getBalance(), user);
        } else {
            throw new InputDataException("CardDTO have someone null value.");
        }
    }

    private List<Transaction> getFromTransactionsById(Long id) {
        return userRepository.getReferenceById(id).getTransactionsFrom();
    }
    private List<Transaction> getToTransactionsById(Long id) {
        return userRepository.getReferenceById(id).getTransactionsTo();
    }

}
