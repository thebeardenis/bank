package org.quick.bank.controllers.api;

import org.quick.bank.models.CardDTO;
import org.quick.bank.services.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @PostMapping("/add_card_to_user")
    public ResponseEntity<String> addCardToUser(@RequestBody CardDTO dto) {
        cardService.addCardById(dto.getUserId(), dto);
        return  ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Card: " + dto.getName() + ", added to user.");
    }
}
