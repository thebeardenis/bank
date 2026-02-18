package org.quick.bank.controllers.api;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.quick.bank.entity.DTOs.TransactionDTO;
import org.quick.bank.entity.models.Transaction;
import org.quick.bank.entity.DTOs.requests.TransactionRequest;
import org.quick.bank.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/transaction")
@Transactional
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/go_transaction")
    public ResponseEntity<Map<String, Object>> transaction(@Valid @ModelAttribute TransactionRequest request) {
        Transaction transaction = transactionService.transaction(request.getCardIdFrom(), request.getCardIdTo(), request.getAmount());
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Card balance changed successfully.");
        result.put("transaction", new TransactionDTO(transaction));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    @PostMapping("/get_transactions_by_user_id/{id}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(transactionsToTransactionsDTOs(transactionService.getTransactionsByUserId(id)));
    }

    @PostMapping("/get_transactions_by_card_id/{id}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByCardId(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(transactionsToTransactionsDTOs(transactionService.getTransactionsByCardId(id)));
    }

    @PostMapping("/get_transaction_by_id/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new TransactionDTO(transactionService.getTransactionById(id)));
    }

    @PostMapping("/get_last_transactions/{count}")
    public ResponseEntity<List<TransactionDTO>> getLastTransactions(@PathVariable("count") Long count) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionsToTransactionsDTOs(transactionService.getLastTransactions(count)));
    }

    private List<TransactionDTO> transactionsToTransactionsDTOs(List<Transaction> transactions) {
        return transactions.stream()
                .map(TransactionDTO::new)
                .toList();
    }
}
