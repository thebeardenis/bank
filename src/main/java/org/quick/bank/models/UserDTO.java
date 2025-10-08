package org.quick.bank.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String email;

    private String password;

    private String name;

    private List<BankCard> cards;

    private List<Transaction> transactionsFrom;

    private List<Transaction> transactionsTo;

    public boolean necessaryFieldIsNull() {
        if (email == null) return true;
        if (password == null) return true;
        return name == null;
    }
     public boolean notNecessaryFieldsIsNotNull() {
        if (cards != null) return true;
        if (transactionsFrom != null) return true;
        return transactionsTo != null;
     }
}
