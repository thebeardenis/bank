package org.quick.bank.entity.DTOs;

import lombok.Data;
import org.quick.bank.entity.models.BankCard;

import java.math.BigDecimal;

@Data
public class BankCardDTO {

    private Long id;
    private String name;
    private BigDecimal balance;

    private Long userId;

    public BankCardDTO(BankCard card) {
        this.id = card.getId();
        this.name = card.getName();
        this.balance = card.getBalance();
        this.userId = card.getUser().getId();
    }
}
