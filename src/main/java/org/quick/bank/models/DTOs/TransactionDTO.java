package org.quick.bank.models.DTOs;

import lombok.*;
import org.quick.bank.models.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDTO {

    private Long idFrom;
    private User userFrom;

    private Long idTo;
    private User userTo;

    private BigDecimal amount;

    private LocalDateTime dealTime;
}

