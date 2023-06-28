package team.retum.jobis.domain.auth.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.auth.domain.AuthCode;
import team.retum.jobis.domain.auth.domain.repository.AuthCodeRepository;
import team.retum.jobis.domain.auth.exception.AuthCodeNotFoundException;
import team.retum.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class VerifyAuthCodeService {

    private final AuthCodeRepository authCodeRepository;

    public void execute(String email, String code) {
        AuthCode authCode = authCodeRepository.findById(email)
                .orElseThrow(() -> AuthCodeNotFoundException.EXCEPTION);
        authCode.verifyCode(code);

        authCodeRepository.save(
                authCode.verify()
        );
    }
}
