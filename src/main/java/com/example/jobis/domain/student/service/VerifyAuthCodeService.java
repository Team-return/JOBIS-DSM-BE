package com.example.jobis.domain.student.service;

import com.example.jobis.domain.student.presentation.dto.request.VerifyAuthCodeRequest;
import com.example.jobis.domain.student.facade.AuthCodeFacade;
import com.example.jobis.global.annotation.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VerifyAuthCodeService {

    private final AuthCodeFacade authCodeFacade;

    public void execute(VerifyAuthCodeRequest request) {
        authCodeFacade.verifyAuthCode(request.getAuthCode(), request.getEmail());
    }
}
