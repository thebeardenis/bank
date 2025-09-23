package org.quick.bank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardDTO {

    private String name;

    private BigDecimal balance;

    private Long id;

    public boolean someoneValuesIsNull() {
        return name == null;
    }
}
