package team.retum.jobis.domain.auth.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.auth.persistence.entity.AuthCodeEntity;
import team.retum.jobis.domain.auth.persistence.repository.AuthCodeRepository;
import team.retum.jobis.domain.auth.exception.AuthCodeNotFoundException;
import com.example.jobisapplication.common.annotation.Service;

@RequiredArgsConstructor
@Service
public class VerifyAuthCodeService {

    private final AuthCodeRepository authCodeRepository;

    public void execute(String email, String code) {
        AuthCodeEntity authCodeEntity = authCodeRepository.findById(email)
                .orElseThrow(() -> AuthCodeNotFoundException.EXCEPTION);
        authCodeEntity.verifyCode(code);

        authCodeRepository.save(
                authCodeEntity.verify()
        );
    }
}
