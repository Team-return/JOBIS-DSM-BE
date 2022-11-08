package com.example.jobis.domain.user.domain.repository;

import com.example.jobis.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByAccountId(String accountId);
}
