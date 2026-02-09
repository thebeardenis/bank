package org.quick.bank.controllers.api;

import jakarta.transaction.Transactional;
import org.quick.bank.entity.models.BankCard;
import org.quick.bank.entity.requests.CreateBankCardRequest;
import org.quick.bank.services.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card")
@Transactional
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @PostMapping("/create_card")
    public ResponseEntity<BankCard> addCardToUser(@RequestBody CreateBankCardRequest request) {
        BankCard card =  cardService.addCardById(request.getUserId(), request.getName());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(card);
    }

    @PostMapping("/delete_card_by_id/{id}")
    public ResponseEntity<String> deleteUserCard(@PathVariable("id") Long id) {
        cardService.deleteCardById(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Success.");
    }

    @PostMapping("/get_card_by_id/{id}")
    public ResponseEntity<BankCard> getCardById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(cardService.getCardById(id));
    }

    @PostMapping("/get_cards_by_user_id/{id}")
    public ResponseEntity<List<BankCard>> getAllUserCards(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardService.getCardsById(id));
    }
}
