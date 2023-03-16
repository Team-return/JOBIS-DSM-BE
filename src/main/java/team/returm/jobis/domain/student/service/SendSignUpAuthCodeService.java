package team.returm.jobis.domain.student.service;

import team.returm.jobis.domain.student.presentation.dto.request.SendAuthCodeRequest;
import team.returm.jobis.domain.student.exception.StudentAlreadyExistsException;
import team.returm.jobis.domain.student.facade.AuthCodeFacade;
import team.returm.jobis.domain.student.facade.StudentFacade;
import team.returm.jobis.global.annotation.Service;
import lombok.RequiredArgsConstructor;

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
