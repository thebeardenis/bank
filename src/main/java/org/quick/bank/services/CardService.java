package org.quick.bank.services;

import lombok.extern.slf4j.Slf4j;
import org.quick.bank.entity.models.BankCard;
import org.quick.bank.entity.models.User;
import org.quick.bank.repositories.BankCardRepository;
import org.quick.bank.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class CardService {

    private final BankCardRepository cardRepository;
    private final UserRepository userRepository;

    public CardService(BankCardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }


    public void addBalanceByCardId(Long card_id, BigDecimal amount) {
        var card = cardRepository.getReferenceById(card_id);
        card.setBalance(card.getBalance().add(amount));
        cardRepository.save(card);
    }

    public BankCard addCardById(Long user_id, String name) {
        User user = userRepository.getReferenceById(user_id);
        var card = new BankCard();
        card.setUser(user);
        card.setBalance(BigDecimal.ZERO);
        card.setName(name);
        card.setUser(user);
        user.addCard(card);
        cardRepository.save(card);
        userRepository.save(user);
        return card;
    }

    public void deleteCardById(Long card_id) {
        log.info("Delete card {}, from user {}", cardRepository.getReferenceById(card_id), cardRepository.getReferenceById(card_id).getUser().getEmail());
        cardRepository.deleteById(card_id);
    }

    public BankCard getCardById(Long card_id) {
        return cardRepository.getReferenceById(card_id);
    }

    public List<BankCard> getCardsById(Long user_id) {
        return userRepository.getReferenceById(user_id).getCards();
    }
}
