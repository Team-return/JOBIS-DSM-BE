package team.returm.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.student.exception.StudentNotFoundException;
import team.returm.jobis.domain.student.facade.AuthCodeFacade;
import team.returm.jobis.domain.student.facade.StudentFacade;
import team.returm.jobis.domain.student.presentation.dto.request.SendAuthCodeRequest;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class SendPasswordAuthCodeService {

    private final AuthCodeFacade authCodeFacade;
    private final StudentFacade studentFacade;

    public void execute(SendAuthCodeRequest request) {

        if (!studentFacade.existsEmail(request.getEmail())) {
            throw StudentNotFoundException.EXCEPTION;
        }
        authCodeFacade.checkEmailDomain(request.getEmail());
        authCodeFacade.sendMail(request.getEmail());
    }
}
