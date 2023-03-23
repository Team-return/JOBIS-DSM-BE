package team.returm.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.student.exception.StudentAlreadyExistsException;
import team.returm.jobis.domain.student.facade.AuthCodeFacade;
import team.returm.jobis.domain.student.presentation.dto.request.SendAuthCodeRequest;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class SendSignUpAuthCodeService {

    private final AuthCodeFacade authCodeFacade;
    private final UserFacade userFacade;

    public void execute(SendAuthCodeRequest request) {

        if (userFacade.existsEmail(request.getEmail())) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }
        authCodeFacade.checkEmailDomain(request.getEmail());
        authCodeFacade.sendMail(request.getEmail());
    }
}
