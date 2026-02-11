package org.quick.bank.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.quick.bank.entity.models.BankCard;
import org.quick.bank.exceptions.BalanceException;
import org.quick.bank.exceptions.InputDataException;
import org.quick.bank.entity.models.Transaction;
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
@Transactional
public class TransactionService {

    private final BankCardRepository bankCardRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(BankCardRepository bankCardRepository, TransactionRepository transactionRepository) {
        this.bankCardRepository = bankCardRepository;
        this.transactionRepository = transactionRepository;
    }


    public Transaction transaction(Long id_from, Long id_to, BigDecimal amount) {
        if (Objects.equals(id_from, id_to)) {
            log.error("Transaction failed id equals. Card1 {}, Card2 {}.", id_from, id_to);
            throw new InputDataException("id first user == id second user");
        }
        addToBalanceById(amount, id_to);
        takeFromBalanceById(amount, id_from);
        var transaction = new Transaction();
        transaction.setCardTo(bankCardRepository.getReferenceById(id_to));
        transaction.setCardFrom(bankCardRepository.getReferenceById(id_from));
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
        log.info(transaction.toString());
        return transaction;
    }

    private void addToBalanceById(BigDecimal amount, Long id) {
        var card = bankCardRepository.getReferenceById(id);
        card.setBalance(card.getBalance().add(amount));
        bankCardRepository.save(card);
    }

    private void takeFromBalanceById(BigDecimal amount, Long id) {
        var card = bankCardRepository.getReferenceById(id);
        if (card.getBalance().compareTo(amount) >= 0) {
            card.setBalance(card.getBalance().subtract(amount));
            bankCardRepository.save(card);
        } else {
            throw new BalanceException("Balance on card: " + card + ", less as request in operation.");
        }
    }

    public List<Transaction> getTransactionsByUserId(Long user_id) {
        List<Transaction> transactions = new ArrayList<>();
        for (BankCard card : bankCardRepository.getBankCardsByUser_Id(user_id)) {
            transactions.addAll(card.getTransactionsTo());
            transactions.addAll(card.getTransactionsFrom());
        }
        transactions.sort(Comparator.comparing(Transaction::getDealTime));
        return transactions;
    }

    public List<Transaction> getTransactionsByCardId(Long card_id) {
        List<Transaction> transactions = new ArrayList<>();
        var card = bankCardRepository.getReferenceById(card_id);
        transactions.addAll(card.getTransactionsTo());
        transactions.addAll(card.getTransactionsFrom());
        transactions.sort(Comparator.comparing(Transaction::getDealTime));
        return transactions;
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.getReferenceById(id);
    }

    public List<Transaction> getLastTransactions(Long count) {
        return transactionRepository.getLastTransactions(count);
    }

}
