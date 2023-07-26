package com.example.jobisapplication.domain.user.spi;

import com.example.jobisapplication.domain.user.model.User;

import java.util.Optional;

public interface QueryUserPort {
    Optional<User> queryUserById(Long userId);

    boolean existsUserByAccountId(String accountId);

    Optional<User> queryUserByAccountId(String accountId);
}
