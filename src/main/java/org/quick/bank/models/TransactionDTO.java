package org.quick.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.quick.bank.models.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDTO {

    private Long id;

    private Long idFrom;
    private User userFrom;

    private Long idTo;
    private User userTo;

    private BigDecimal amount;

    private LocalDateTime dealTime;
}

