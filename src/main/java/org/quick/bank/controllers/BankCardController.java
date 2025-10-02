package org.quick.bank.controllers;

import org.quick.bank.models.TransactionDTO;
import org.quick.bank.services.BankTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankCardController {

    private final BankTransactionService bankTransactionService;

    public BankCardController(BankTransactionService bankTransactionService) {
        this.bankTransactionService = bankTransactionService;
    }


    @PostMapping("/transaction")
    public ResponseEntity<String> changeBalance(@RequestBody TransactionDTO dto) {
        bankTransactionService.transaction(dto.getId_from(), dto.getId_to(), dto.getAmount());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Card balance changed successful.");
    }
}
