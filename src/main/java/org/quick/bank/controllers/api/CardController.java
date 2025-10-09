package org.quick.bank.controllers.api;

import org.quick.bank.models.BankCard;
import org.quick.bank.models.CardDTO;
import org.quick.bank.services.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
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
    public ResponseEntity<BankCard> getCardById(Long id) {
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
