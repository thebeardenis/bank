package org.quick.bank.services;

import lombok.extern.slf4j.Slf4j;
import org.quick.bank.exceptions.BalanceException;
import org.quick.bank.exceptions.InputDataException;
import org.quick.bank.models.Transaction;
import org.quick.bank.repositories.BankCardRepository;
import org.quick.bank.repositories.TransactionRepository;
import org.quick.bank.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class TransactionService {

    private final BankCardRepository bankCardRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(BankCardRepository bankCardRepository, TransactionRepository transactionRepository, UserRepository userRepository) {
        this.bankCardRepository = bankCardRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }


    public Long transaction(Long id_from, Long id_to, BigDecimal amount) {
        if (Objects.equals(id_from, id_to)) {
            throw new InputDataException("id first user = id second user");
        }
        addToBalanceById(amount, id_to);
        takeFromBalanceById(amount, id_from);
        var transaction = new Transaction();
        transaction.setUserFrom(bankCardRepository.getReferenceById(id_from).getUserCard());
        transaction.setUserTo(bankCardRepository.getReferenceById(id_to).getUserCard());
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
        log.info("Saving transaction: {}", transaction);
        return transaction.getId();
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

    public List<Transaction> getTransactionsByUserId(Long id) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(getFromTransactionsById(id));
        transactions.addAll(getToTransactionsById(id));
        transactions.sort(Comparator.comparing(Transaction::getDealTime));
        return transactions;
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.getReferenceById(id);
    }

    private List<Transaction> getFromTransactionsById(Long id) {
        return userRepository.getReferenceById(id).getTransactionsFrom();
    }

    private List<Transaction> getToTransactionsById(Long id) {
        return userRepository.getReferenceById(id).getTransactionsTo();
    }

    public List<Transaction> getLastTransactions(Long count) {
        return transactionRepository.getLastTransactions(count);
    }

}
