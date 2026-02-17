package org.quick.bank.entity.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.quick.bank.entity.models.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {

    private Long id;
    private BigDecimal amount;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dealTime;

    private Long CardFromId;
    private String CardFromName;

    private Long CardToId;
    private String CardToName;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.dealTime = transaction.getDealTime();

        this.CardFromId = transaction.getCardFrom().getId();
        this.CardFromName = transaction.getCardFrom().getName();

        this.CardToId = transaction.getCardTo().getId();
        this.CardToName = transaction.getCardTo().getName();
    }
}
