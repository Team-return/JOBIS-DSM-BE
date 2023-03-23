package team.returm.jobis.domain.auth.facade;

import team.returm.jobis.domain.auth.domain.AuthCode;
import team.returm.jobis.domain.student.domain.repository.AuthCodeRepository;
import team.returm.jobis.domain.student.exception.BadAuthCodeException;
import team.returm.jobis.domain.student.exception.BadEmailException;
import team.returm.jobis.domain.student.exception.StudentAlreadyExistsException;
import team.returm.jobis.domain.student.exception.StudentNotFoundException;
import team.returm.jobis.domain.student.exception.UnverifiedEmailException;
import team.returm.jobis.domain.student.facade.StudentFacade;
import team.returm.jobis.global.util.RegexProperty;
import team.returm.jobis.global.util.jms.JmsProperties;
import team.returm.jobis.global.util.jms.JmsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@RequiredArgsConstructor
@Component
public class AuthCodeFacade {

    private static final SecureRandom RANDOM = new SecureRandom();
    private final AuthCodeRepository authCodeRepository;
    private final JmsUtil jmsUtil;
    private final JmsProperties jmsProperties;
    private final StudentFacade studentFacade;

    public void sendSignUpAuthCode(String email) {

        if (studentFacade.existsEmail(email)) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }
        sendMail(email);
    }

    public void sendPasswordAuthCode(String email) {

        if (!studentFacade.existsEmail(email)) {
            throw StudentNotFoundException.EXCEPTION;
        }
        sendMail(email);
    }

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

    public void sendMail(String email) {
        String code = createRandomCode();
        AuthCode authCode = getAuthCode(email, code);
        authCodeRepository.save(authCode);

        jmsUtil.sendMail(email, authCode.getCode());
    }

    private AuthCode getAuthCode(String email, String code) {
        AuthCode authCode = authCodeRepository.findById(email)
                .orElseGet(() -> createAuthCode(email, code));

        authCode.updateAuthCode(code, jmsProperties.getAuthExp());
        return authCode;
    }

    private AuthCode createAuthCode(String email, String code) {
        return AuthCode.builder()
                .email(email)
                .code(code)
                .isVerified(false)
                .ttl(jmsProperties.getAuthExp())
                .build();
    }

    private String createRandomCode() {
        return String.format("%06d", RANDOM.nextInt(1000000) % 1000000);
    }
}
