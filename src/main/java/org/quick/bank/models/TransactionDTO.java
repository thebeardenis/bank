package org.quick.bank.models;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDTO {

    private Long id_from;
    private Long id_to;
    private BigDecimal amount;
}
