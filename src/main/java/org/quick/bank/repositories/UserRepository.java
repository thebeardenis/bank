package org.quick.bank.repositories;


import org.quick.bank.entity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(String email);
}
