package org.quick.bank.controllers.api;


import jakarta.transaction.Transactional;
import org.quick.bank.entity.DTOs.requests.ChangeBalanceCardRequest;
import org.quick.bank.services.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@Transactional
public class AdminController {

    private final CardService cardService;

    public AdminController(CardService cardService) {
        this.cardService = cardService;
    };

    @PostMapping("/add_amount_by_card_id")
    public ResponseEntity<String> addAmountByCardId( @RequestBody ChangeBalanceCardRequest request) {
        cardService.addBalanceByCardId(request.getCardId(), request.getAmount());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Success.");
    }
}
