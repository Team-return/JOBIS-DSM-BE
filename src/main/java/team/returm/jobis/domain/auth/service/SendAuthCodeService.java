package team.returm.jobis.domain.auth.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.auth.domain.types.AuthCodeType;
import team.returm.jobis.domain.auth.facade.AuthCodeFacade;
import team.returm.jobis.domain.auth.presentation.dto.request.SendAuthCodeRequest;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class SendAuthCodeService {

    private final AuthCodeFacade authCodeFacade;

    public void execute(SendAuthCodeRequest request) {

        if (request.getAuthCodeType().equals(AuthCodeType.SIGN_UP)) {
            authCodeFacade.sendSignUpAuthCode(request.getEmail());
        } else {
            authCodeFacade.sendPasswordAuthCode(request.getEmail());
        }
    }
}
