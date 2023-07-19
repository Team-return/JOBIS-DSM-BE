package com.example.jobisapplication.domain.auth.usecase;

import com.example.jobisapplication.domain.auth.model.AuthCode;
import com.example.jobisapplication.domain.auth.spi.AuthCodePort;
import com.example.jobisapplication.domain.auth.spi.CommandAuthCodePort;
import com.example.jobisapplication.domain.auth.spi.QueryAuthCodePort;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.auth.persistence.entity.AuthCodeEntity;
import team.retum.jobis.domain.auth.persistence.repository.AuthCodeRepository;
import team.retum.jobis.domain.auth.exception.AuthCodeNotFoundException;
import com.example.jobisapplication.common.annotation.Service;

@RequiredArgsConstructor
@Service
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
