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


    public void addBalanceByCardId(Long id, BigDecimal amount) {
        var card = cardRepository.getReferenceById(id);
        card.setBalance(card.getBalance().add(amount));
        cardRepository.save(card);
    }

    public BankCard addCardById(Long id, String name) {
        User user = userRepository.getReferenceById(id);
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

    public void deleteCardById(Long id) {
        cardRepository.deleteById(id);
    }

    public BankCard getCardById(Long id) {
        return cardRepository.getReferenceById(id);
    }

    public List<BankCard> getCardsById(Long id) {
        return userRepository.getReferenceById(id).getCards();
    }
}
