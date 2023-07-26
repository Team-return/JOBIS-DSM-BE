package com.example.jobisapplication.domain.student.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.user.exception.UserNotFoundException;
import com.example.jobisapplication.domain.user.model.User;
import com.example.jobisapplication.domain.user.spi.QueryUserPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.user.exception.InvalidPasswordException;

@RequiredArgsConstructor
@UseCase
public class CheckStudentPasswordUseCase {

    private final SecurityPort securityPort;
    private final QueryUserPort queryUserPort;

    public void execute(String password) {
        Long currentUserId = securityPort.getCurrentUserId();
        User user = queryUserPort.queryUserById(currentUserId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!securityPort.isPasswordMatch(password, user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }
    }
}
