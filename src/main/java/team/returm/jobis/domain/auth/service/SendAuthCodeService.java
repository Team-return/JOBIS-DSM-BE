package team.returm.jobis.domain.auth.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.auth.domain.types.AuthCodeType;
import team.returm.jobis.domain.auth.facade.AuthCodeFacade;
import team.returm.jobis.domain.auth.presentation.dto.request.SendAuthCodeRequest;
import team.returm.jobis.domain.student.exception.StudentAlreadyExistsException;
import team.returm.jobis.domain.student.exception.StudentNotFoundException;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class SendAuthCodeService {

    private final AuthCodeFacade authCodeFacade;
    private final UserFacade userFacade;

    public void execute(SendAuthCodeRequest request) {
        String code = authCodeFacade.createRandomCode();
        authCodeFacade.getAuthCode(request.getEmail(), code);

        if (request.getAuthCodeType().equals(AuthCodeType.SIGN_UP)) {
            sendSignUpAuthCode(request.getEmail(), code);
        } else {
            sendPasswordAuthCode(request.getEmail(), code);
        }
    }

    private void sendSignUpAuthCode(String email, String code) {

        if (userFacade.existsAccountId(email)) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }
        authCodeFacade.sendMail(email, code);
    }

    private void sendPasswordAuthCode(String email, String code) {

        if (!userFacade.existsAccountId(email)) {
            throw StudentNotFoundException.EXCEPTION;
        }
        authCodeFacade.sendMail(email, code);
    }
}
