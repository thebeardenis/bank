package org.quick.bank.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
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
    public void changeBalance(BigDecimal amount, String name, Long id) {
        var res = bankCardRepository.getReferenceById(id);
        BigDecimal result = res.getBalance().add(amount);
        res.setBalance(result);
        bankCardRepository.save(res);
        log.info("Card {}: changed balance to: {}", name, result);
    }

}
