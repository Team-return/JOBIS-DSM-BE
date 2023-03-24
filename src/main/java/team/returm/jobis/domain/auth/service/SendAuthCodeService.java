package team.returm.jobis.domain.auth.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.auth.domain.types.AuthCodeType;
import team.returm.jobis.domain.auth.facade.AuthCodeFacade;
import team.returm.jobis.domain.auth.presentation.dto.request.SendAuthCodeRequest;
import team.returm.jobis.domain.student.exception.StudentAlreadyExistsException;
import team.returm.jobis.domain.student.exception.StudentNotFoundException;
import team.returm.jobis.domain.student.facade.StudentFacade;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class SendAuthCodeService {

    private final AuthCodeFacade authCodeFacade;
    private final StudentFacade studentFacade;

    public void execute(SendAuthCodeRequest request) {

        if (request.getAuthCodeType().equals(AuthCodeType.SIGN_UP)) {
            sendSignUpAuthCode(request.getEmail());
        } else {
            sendPasswordAuthCode(request.getEmail());
        }
    }

    private void sendSignUpAuthCode(String email) {

        if (studentFacade.existsEmail(email)) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }
        authCodeFacade.sendMail(email);
    }

    private void sendPasswordAuthCode(String email) {

        if (!studentFacade.existsEmail(email)) {
            throw StudentNotFoundException.EXCEPTION;
        }
        authCodeFacade.sendMail(email);
    }
}
