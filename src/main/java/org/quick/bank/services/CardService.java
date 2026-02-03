package org.quick.bank.services;

import lombok.extern.slf4j.Slf4j;
import org.quick.bank.exceptions.InputDataException;
import org.quick.bank.models.BankCard;
import org.quick.bank.models.CardDTO;
import org.quick.bank.models.User;
import org.quick.bank.repositories.BankCardRepository;
import org.quick.bank.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.smartcardio.Card;
import java.math.BigDecimal;
import java.util.ArrayList;
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


    public void addCardById(Long id, CardDTO dto) {
        if (dto.getName() != null) {
            User user = userRepository.getReferenceById(id);
            var card = new BankCard();
            card.setBalance(dto.getBalance() == null ? BigDecimal.ZERO : dto.getBalance());
            card.setName(dto.getName());
            card.setUserCard(user);
            cardRepository.save(card);
            userRepository.save(user);
            log.info("Card {}, with balance {}, added to user {}", card.getName(), card.getBalance(), user);
        } else {
            throw new InputDataException("Someone necessary field in cardDTO is null.");
        }
    }

    public void deleteCardById(CardDTO dto) {
        if (dto.getId() != null) {
            cardRepository.deleteById(dto.getId());
        } else {
            throw new InputDataException("card id is null");
        }
    }

    public BankCard getCardById(Long id) {
        return cardRepository.getReferenceById(id);
    }

    public List<BankCard> getCardsById(Long id) {
        return userRepository.getReferenceById(id).getCards();
    }
}
