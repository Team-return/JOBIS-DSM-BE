package team.retum.jobis.domain.auth.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.auth.exception.AuthCodeNotFoundException;
import team.retum.jobis.domain.auth.model.AuthCode;
import team.retum.jobis.domain.auth.persistence.mapper.AuthCodeMapper;
import team.retum.jobis.domain.auth.persistence.repository.AuthCodeRepository;
import team.retum.jobis.domain.auth.spi.AuthCodePort;

@RequiredArgsConstructor
@Component
public class AuthCodePersistenceAdapter implements AuthCodePort {

    private final AuthCodeRepository authCodeRepository;
    private final AuthCodeMapper authCodeMapper;

    @Override
    public void saveAuthCode(AuthCode authCode) {
        authCodeRepository.save(
                authCodeMapper.toEntity(authCode)
        );
    }

    @Override
    public AuthCode queryAuthCodeByEmail(String email) {
        return authCodeMapper.toDomain(
                authCodeRepository.findById(email)
                        .orElseThrow(() -> AuthCodeNotFoundException.EXCEPTION)
        );
    }

    @Override
    public boolean existsAuthCodeByEmailAndVerifiedIsTrue(String email) {
        return authCodeRepository.existsByEmailAndVerifiedIsTrue(email);
    }
}
