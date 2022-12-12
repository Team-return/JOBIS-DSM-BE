package com.example.jobis.domain.student.service;

import com.example.jobis.domain.student.controller.dto.request.SendAuthCodeRequest;
import com.example.jobis.domain.student.exception.StudentAlreadyExistsException;
import com.example.jobis.domain.student.facade.AuthCodeFacade;
import com.example.jobis.domain.student.facade.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SendSignUpAuthCodeService {

    private final AuthCodeFacade authCodeFacade;
    private final StudentFacade studentFacade;

    public void execute(SendAuthCodeRequest request) {

        if (studentFacade.existsEmail(request.getEmail())) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }
        authCodeFacade.checkEmailDomain(request.getEmail());
        authCodeFacade.sendMail(request.getEmail());
    }
}
