package org.quick.bank.controllers;

import org.quick.bank.models.CardDTO;
import org.quick.bank.repositories.BankCardRepository;
import org.quick.bank.services.BankCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankCardController {

    private final BankCardService bankCardService;

    public BankCardController(BankCardService bankCardService) {
        this.bankCardService = bankCardService;
    }


    @PostMapping("/changeBalance/{card_id}")
    public ResponseEntity<String> changeBalance(@PathVariable("card_id") Long cardId, @RequestBody CardDTO card) {
        bankCardService.changeBalance(card.getBalance(), card.getName(), cardId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Card balance changed successful.");
    }
}
