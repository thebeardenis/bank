package org.quick.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardDTO {

    private Long id;

    private BigDecimal balance;

    private String name;

    private Long userId;
    private User userCard;

    public boolean necessaryFieldIsNull() {
        return name == null;
    }
    public boolean notNecessaryFieldsIsNotNull() {
        if (balance != null) return true;
        return userCard != null;
    }
}
