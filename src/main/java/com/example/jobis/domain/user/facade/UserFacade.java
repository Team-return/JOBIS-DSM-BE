package com.example.jobis.domain.user.facade;

import com.example.jobis.domain.user.domain.User;
import com.example.jobis.domain.user.domain.repository.UserJpaRepository;
import com.example.jobis.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserJpaRepository userRepository;

    public User getUser(String accountId) {
        return userRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
