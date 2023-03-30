package team.returm.jobis.domain.auth.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.returm.jobis.domain.student.domain.repository.AuthCodeRepository;
import team.returm.jobis.domain.student.exception.UnverifiedEmailException;

@RequiredArgsConstructor
@Component
public class AuthCodeFacade {

    private final AuthCodeRepository authCodeRepository;

    public void checkIsVerified(String email) {
        if (!authCodeRepository.existsByEmailAndVerifiedIsTrue(email)) {
            throw UnverifiedEmailException.EXCEPTION;
        }
    }
}
