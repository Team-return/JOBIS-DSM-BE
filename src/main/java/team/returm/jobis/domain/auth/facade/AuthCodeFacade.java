package team.returm.jobis.domain.auth.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.returm.jobis.domain.auth.domain.AuthCode;
import team.returm.jobis.domain.student.domain.repository.AuthCodeRepository;
import team.returm.jobis.domain.student.exception.BadAuthCodeException;
import team.returm.jobis.domain.student.exception.UnverifiedEmailException;
import team.returm.jobis.global.util.jms.JmsProperties;
import team.returm.jobis.global.util.jms.JmsUtil;

import java.security.SecureRandom;

@RequiredArgsConstructor
@Component
public class AuthCodeFacade {

    private static final SecureRandom RANDOM = new SecureRandom();
    private final AuthCodeRepository authCodeRepository;
    private final JmsUtil jmsUtil;
    private final JmsProperties jmsProperties;

    public void verifyAuthCode(String code, String email) {
        AuthCode authCode = authCodeRepository.findById(email)
                .filter(a ->  a.getCode().equals(code))
                .orElseThrow(() -> BadAuthCodeException.EXCEPTION);
        authCode.verify();
        authCodeRepository.save(authCode);
    }

    public void checkIsVerified(String email) {
        if (!authCodeRepository.existsByEmailAndVerifiedIsTrue(email)) {
            throw UnverifiedEmailException.EXCEPTION;
        }
    }

    public void sendMail(String email, String code) {
        jmsUtil.sendMail(email, code);
    }

    public void getAuthCode(String email, String code) {
        AuthCode authCode = authCodeRepository.findById(email)
                .orElseGet(() -> createAuthCode(email, code));

        authCode.updateAuthCode(code, jmsProperties.getAuthExp());
    }

    private AuthCode createAuthCode(String email, String code) {
        return AuthCode.builder()
                .email(email)
                .code(code)
                .isVerified(false)
                .ttl(jmsProperties.getAuthExp())
                .build();
    }

    public String createRandomCode() {
        return String.format("%06d", RANDOM.nextInt(1000000) % 1000000);
    }
}
