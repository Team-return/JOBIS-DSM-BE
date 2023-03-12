package com.example.jobis.domain.user.domain.repository;

import com.example.jobis.domain.user.domain.User;
import com.example.jobis.domain.user.exception.UserNotFoundException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import static com.example.jobis.domain.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final JPAQueryFactory queryFactory;

    public Optional<User> queryUserByAccountId(String accountId) {
        return userJpaRepository.findByAccountId(accountId);
    }

    public User saveUser(User user) {
        return userJpaRepository.save(user);
    }

    public Optional<User> queryUserById(UUID userId) {
        return userJpaRepository.findById(userId);
    }
}
