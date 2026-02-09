package org.quick.bank.entity.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
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

    public void addCard(BankCard card) {
        cards.add(card);
    }
}
