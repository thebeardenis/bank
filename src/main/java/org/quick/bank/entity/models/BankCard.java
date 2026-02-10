package org.quick.bank.entity.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Table(name = "cards")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
