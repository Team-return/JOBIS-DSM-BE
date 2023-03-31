package team.returm.jobis.domain.auth.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.auth.domain.AuthCode;
import team.returm.jobis.domain.auth.domain.types.AuthCodeType;
import team.returm.jobis.domain.auth.presentation.dto.request.SendAuthCodeRequest;
import team.returm.jobis.domain.student.domain.repository.AuthCodeRepository;
import team.returm.jobis.domain.student.exception.StudentAlreadyExistsException;
import team.returm.jobis.domain.student.exception.StudentNotFoundException;
import team.returm.jobis.domain.user.domain.repository.UserRepository;
import team.returm.jobis.global.annotation.Service;
import team.returm.jobis.global.util.StringUtil;
import team.returm.jobis.global.util.jms.JmsUtil;

@RequiredArgsConstructor
@Service
public class SendAuthCodeService {

    private final AuthCodeRepository authCodeRepository;
    private final UserRepository userRepository;
    private final JmsUtil jmsUtil;

    public void execute(SendAuthCodeRequest request) {
        if (request.getAuthCodeType() == AuthCodeType.SIGN_UP) {
            if (userRepository.existsByAccountId(request.getEmail())) {
                throw StudentAlreadyExistsException.EXCEPTION;
            }
        } else {
            if (!userRepository.existsByAccountId(request.getEmail())) {
                throw StudentNotFoundException.EXCEPTION;
            }
        }

        AuthCode authCode = AuthCode.builder()
                .code(StringUtil.generateRandomCode(6))
                .email(request.getEmail())
                .build();
        authCodeRepository.save(authCode);

        jmsUtil.sendMail(authCode.getEmail(), authCode.getCode(), request.getUserName());
    }
}
