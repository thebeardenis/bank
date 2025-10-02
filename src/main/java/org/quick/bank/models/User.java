package org.quick.bank.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCard")
    private List<BankCard> cards;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userFrom")
    private List<Transaction> transactionsFrom;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userTo")
    private List<Transaction> transactionsTo;

    @Override
    public String toString() {
        return "id: " + id + ", email: " + email + ", name: " + name;
    }

    public User() {}

    public void addCard(BankCard card) {
        if (cards == null) {
            this.cards = new ArrayList<>();
        }
        cards.add(card);
        card.setUserCard(this);
    }
}
