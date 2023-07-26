package com.example.jobisapplication.domain.user.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.auth.dto.TokenResponse;
import com.example.jobisapplication.domain.auth.spi.JwtPort;
import com.example.jobisapplication.domain.user.dto.LoginRequest;
import com.example.jobisapplication.domain.user.exception.UserNotFoundException;
import com.example.jobisapplication.domain.user.model.User;
import com.example.jobisapplication.domain.user.spi.QueryUserPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.user.exception.InvalidPasswordException;

@RequiredArgsConstructor
@UseCase
public class LoginUseCase {

    private final QueryUserPort queryUserPort;
    private final SecurityPort securityPort;
    private final JwtPort jwtPort;

    public TokenResponse execute(LoginRequest request) {

        User user = queryUserPort.queryUserByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!securityPort.isPasswordMatch(request.getPassword(), user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        return jwtPort.generateTokens(user.getId(), user.getAuthority());
    }
}
