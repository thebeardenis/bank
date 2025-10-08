package org.quick.bank.controllers.api;

import jakarta.transaction.Transactional;
import org.quick.bank.models.TransactionDTO;
import org.quick.bank.services.BankTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class BankCardController {

    private final BankTransactionService bankTransactionService;

    public BankCardController(BankTransactionService bankTransactionService) {
        this.bankTransactionService = bankTransactionService;
    }


    @PostMapping("/transaction")
    public ResponseEntity<String> transaction(@RequestBody TransactionDTO dto) {
        bankTransactionService.transaction(dto.getIdFrom(), dto.getIdTo(), dto.getAmount());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Card balance changed successful.");
    }
}
