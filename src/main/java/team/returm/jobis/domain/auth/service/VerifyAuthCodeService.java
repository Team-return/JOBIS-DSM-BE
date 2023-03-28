package team.returm.jobis.domain.auth.service;

import team.returm.jobis.domain.auth.presentation.dto.request.VerifyAuthCodeRequest;
import team.returm.jobis.domain.auth.facade.AuthCodeFacade;
import team.returm.jobis.global.annotation.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VerifyAuthCodeService {

    private final AuthCodeFacade authCodeFacade;

    public void execute(String email, String authCode) {
        authCodeFacade.verifyAuthCode(email, authCode);
    }
}
