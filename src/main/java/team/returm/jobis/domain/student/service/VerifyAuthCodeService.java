package team.returm.jobis.domain.student.service;

import team.returm.jobis.domain.student.presentation.dto.request.VerifyAuthCodeRequest;
import team.returm.jobis.domain.student.facade.AuthCodeFacade;
import team.returm.jobis.global.annotation.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VerifyAuthCodeService {

    private final AuthCodeFacade authCodeFacade;

    public void execute(VerifyAuthCodeRequest request) {
        authCodeFacade.verifyAuthCode(request.getAuthCode(), request.getEmail());
    }
}
