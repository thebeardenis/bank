package org.quick.bank.repositories;

import org.quick.bank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t JOIN FETCH t.users WHERE t.id = :id")
    Optional<Transaction> findByIdWithUsers(@Param("id") Long id);

    List<Transaction> findByUsersId(Long userId);
}
