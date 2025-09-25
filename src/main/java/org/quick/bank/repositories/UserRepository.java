package org.quick.bank.repositories;


import org.quick.bank.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN FETCH u.transactions WHERE u.id = :id")
    Optional<User> findByIdWithTransactions(@Param("id") Long id);

    @Query("SELECT DISTINCT u FROM User u JOIN u.transactions t WHERE t.id = :transactionId")
    List<User> findUsersByTransactionId(@Param("transactionId") Long transactionId);
}
