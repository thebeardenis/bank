package org.quick.bank.repositories;

import org.quick.bank.entity.models.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankCardRepository extends JpaRepository<BankCard, Long> {

    List<BankCard> getBankCardsByUser_Id(Long id);
}
