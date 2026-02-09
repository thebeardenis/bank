package org.quick.bank.controllers.api;

import jakarta.transaction.Transactional;
import org.quick.bank.models.BankCard;
import org.quick.bank.models.DTOs.CardDTO;
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


    @PostMapping("/add_user_card")
    public ResponseEntity<String> addCardToUser(@RequestBody CardDTO dto) {
        cardService.addCardById(dto.getUserId(), dto);
        return  ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Card: " + dto.getName() + ", added to user.");
    }

    @PostMapping("/delete_user_card")
    public ResponseEntity<String> deleteUserCard(@RequestBody CardDTO dto) {
        cardService.deleteCardById(dto);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Card " + dto.getId() + " deleted.");
    }

    @PostMapping("/get_user_card_by_id/{id}")
    public ResponseEntity<BankCard> getCardById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(cardService.getCardById(id));
    }

    @PostMapping("/get_user_cards_by_id/{id}")
    public ResponseEntity<List<BankCard>> getAllUserCards(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardService.getCardsById(id));
    }
}
