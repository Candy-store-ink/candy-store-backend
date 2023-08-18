package com.github.candy.store.modules.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByToken(String token);

    @Modifying
    @Transactional
    @Query("update Token t set t.revoked = true where t.token = ?1")
    void revokeToken(String token);
}
