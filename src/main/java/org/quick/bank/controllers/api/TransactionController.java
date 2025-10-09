package org.quick.bank.controllers.api;

import jakarta.transaction.Transactional;
import org.quick.bank.models.Transaction;
import org.quick.bank.models.TransactionDTO;
import org.quick.bank.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/transaction")
    public ResponseEntity<String> transaction(@RequestBody TransactionDTO dto) {
        transactionService.transaction(dto.getIdFrom(), dto.getIdTo(), dto.getAmount());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Card balance changed successful.");
    }

    @PostMapping("/get_transactions_by_id/{id}")
    public ResponseEntity<List<Transaction>> getTransactionsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(transactionService.getTransactionsByUserId(id));
    }
}
