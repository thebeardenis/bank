package org.quick.bank.controllers.api;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.quick.bank.entity.models.Transaction;
import org.quick.bank.entity.requests.TransactionRequest;
import org.quick.bank.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        result.put("transaction", transaction);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    @PostMapping("/get_transactions_by_user_id/{id}")
    public ResponseEntity<List<Transaction>> getTransactionsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(transactionService.getTransactionsByUserId(id));
    }

    @PostMapping("/get_transaction_by_id/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(transactionService.getTransactionById(id));
    }

    @PostMapping("/get_last_transactions/{count}")
    public ResponseEntity<List<Transaction>> getLastTransactions(@PathVariable("count") Long count) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionService.getLastTransactions(count));
    }
}
