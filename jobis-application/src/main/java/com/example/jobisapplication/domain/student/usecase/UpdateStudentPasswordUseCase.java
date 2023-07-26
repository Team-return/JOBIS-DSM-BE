package com.example.jobisapplication.domain.student.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.student.dto.UpdatePasswordRequest;
import com.example.jobisapplication.domain.user.exception.UserNotFoundException;
import com.example.jobisapplication.domain.user.model.User;
import com.example.jobisapplication.domain.user.spi.CommandUserPort;
import com.example.jobisapplication.domain.user.spi.QueryUserPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.user.exception.InvalidPasswordException;

@RequiredArgsConstructor
@UseCase
public class UpdateStudentPasswordUseCase {

    private final SecurityPort securityPort;
    private final QueryUserPort queryUserPort;
    private final CommandUserPort commandUserPort;

    public void execute(UpdatePasswordRequest request) {
        Long currentUserId = securityPort.getCurrentUserId();
        User user = queryUserPort.queryUserById(currentUserId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!securityPort.isPasswordMatch(request.getCurrentPassword(), user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        commandUserPort.saveUser(
                user.updatePassword(securityPort.encodePassword(request.getNewPassword()))
        );
    }
}
