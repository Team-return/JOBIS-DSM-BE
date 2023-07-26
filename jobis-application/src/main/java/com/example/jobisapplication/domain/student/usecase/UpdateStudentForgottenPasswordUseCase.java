package com.example.jobisapplication.domain.student.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.auth.model.AuthCode;
import com.example.jobisapplication.domain.auth.spi.QueryAuthCodePort;
import com.example.jobisapplication.domain.student.dto.UpdateForgottenPasswordRequest;
import com.example.jobisapplication.domain.user.model.User;
import com.example.jobisapplication.domain.user.spi.QueryUserPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.user.exception.UserNotFoundException;

@RequiredArgsConstructor
@UseCase
public class UpdateStudentForgottenPasswordUseCase {

    private final QueryUserPort queryUserPort;
    private final QueryAuthCodePort queryAuthCodePort;
    private final SecurityPort securityPort;

    public void execute(UpdateForgottenPasswordRequest request) {
        if (!queryUserPort.existsUserByAccountId(request.getEmail())) {
            throw UserNotFoundException.EXCEPTION;
        }

        AuthCode authCodeEntity = queryAuthCodePort.queryAuthCodeByEmail(request.getEmail());
        authCodeEntity.checkIsVerified();

        User userEntity = queryUserPort.queryUserByAccountId(request.getEmail())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        userEntity.updatePassword(securityPort.encodePassword(request.getPassword()));
    }
}
