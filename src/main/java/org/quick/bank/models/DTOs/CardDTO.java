package org.quick.bank.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.quick.bank.models.User;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardDTO {

    private Long id;

    private BigDecimal balance;

    private String name;

    private Long userId;
    private User user;

}
