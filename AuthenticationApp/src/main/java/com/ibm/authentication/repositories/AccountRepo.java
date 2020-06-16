package com.ibm.authentication.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.ibm.authentication.models.Account;

public interface AccountRepo extends Repository<Account, Long> {

    Collection<Account> findAll();
    Optional<Account> findByUsername(String username);
    Optional<Account> findById(Long id);
    Integer countByUsername(String username);
    Account save(Account account);
    void deleteAccountById(Long id);



}
