package com.example.jobisapplication.domain.auth.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.auth.model.AuthCode;
import com.example.jobisapplication.domain.auth.spi.CommandAuthCodePort;
import com.example.jobisapplication.domain.auth.spi.QueryAuthCodePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class VerifyAuthCodeService {

    private final CommandAuthCodePort commandAuthCodePort;
    private final QueryAuthCodePort queryAuthCodePort;

    public void execute(String email, String code) {
        AuthCode authCode = queryAuthCodePort.queryAuthCodeByEmail(email);
        authCode.verifyCode(code);

        commandAuthCodePort.saveAuthCode(
                authCode.verify()
        );
    }
}
