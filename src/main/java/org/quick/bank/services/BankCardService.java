package org.quick.bank.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.quick.bank.exceptions.BalanceException;
import org.quick.bank.repositories.BankCardRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class BankCardService {

    private final BankCardRepository bankCardRepository;

    public BankCardService(BankCardRepository bankCardRepository) {
        this.bankCardRepository = bankCardRepository;
    }

    @Transactional
    public void transaction(Long id_from, Long id_to, BigDecimal amount) {
        addToBalanceById(amount, id_to);
        takeFromBalanceById(amount, id_from);
    }

    private void addToBalanceById(BigDecimal amount, Long id) {
        var card = bankCardRepository.getReferenceById(id);
        card.setBalance(card.getBalance().add(amount));
        bankCardRepository.save(card);
        log.info("Card with id {}: added to balance: {}, and now balance: {}", id, amount, card.getBalance());
    }

    private void takeFromBalanceById(BigDecimal amount, Long id) {
        var card = bankCardRepository.getReferenceById(id);
        if (card.getBalance().compareTo(amount) >= 0) {
            card.setBalance(card.getBalance().subtract(amount));
            bankCardRepository.save(card);
            log.info("Card with id {}: take from balance: {}, and now balance: {}", id, amount, card.getBalance());
        } else {
            throw new BalanceException("Balance on card: " + card + ", less as request in operation.");
        }
    }

}
