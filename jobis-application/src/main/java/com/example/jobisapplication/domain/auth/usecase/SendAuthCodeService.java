package com.example.jobisapplication.domain.auth.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.SesPort;
import com.example.jobisapplication.domain.auth.dto.SendAuthCodeRequest;
import com.example.jobisapplication.domain.auth.model.AuthCode;
import com.example.jobisapplication.domain.auth.spi.CommandAuthCodePort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.auth.model.AuthCodeType;
import com.example.jobisapplication.domain.student.exception.StudentAlreadyExistsException;
import com.example.jobisapplication.domain.student.exception.StudentNotFoundException;
import com.example.jobisapplication.common.util.StringUtil;

@RequiredArgsConstructor
@UseCase
public class SendAuthCodeService {

    private final CommandAuthCodePort commandAuthCodePort;
    private final UserRepository userRepository;
    private final SesPort sesPort;

    public void execute(SendAuthCodeRequest request) {
        if (request.getAuthCodeType() == AuthCodeType.SIGN_UP) {
            if (userRepository.existsByAccountId(request.getEmail())) {
                throw StudentAlreadyExistsException.EXCEPTION;
            }
        } else {
            if (!userRepository.existsByAccountId(request.getEmail())) {
                throw StudentNotFoundException.EXCEPTION;
            }
        }

        AuthCode authCode = AuthCode.builder()
                .code(StringUtil.generateRandomCode(6))
                .ttl(300)
                .isVerified(false)
                .email(request.getEmail())
                .build();
        commandAuthCodePort.saveAuthCode(authCode);

        sesPort.sendMail(authCode.getCode(), authCode.getEmail());
    }
}
