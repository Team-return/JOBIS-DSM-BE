package team.retum.jobis.domain.auth.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.auth.persistence.entity.AuthCodeEntity;
import team.retum.jobis.domain.auth.persistence.repository.AuthCodeRepository;
import team.retum.jobis.domain.auth.persistence.types.AuthCodeType;
import team.retum.jobis.domain.auth.presentation.dto.request.SendAuthCodeRequest;
import com.example.jobisapplication.domain.student.exception.StudentAlreadyExistsException;
import com.example.jobisapplication.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.auth.presentation.dto.request.SendAuthCodeWebRequest;
import team.retum.jobis.domain.student.exception.StudentAlreadyExistsException;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.user.persistence.repository.UserRepository;
import com.example.jobisapplication.common.annotation.Service;
import com.example.jobisapplication.common.util.StringUtil;
import team.retum.jobis.thirdparty.ses.SesUtil;

@RequiredArgsConstructor
@Service
public class SendAuthCodeService {

    private final AuthCodeRepository authCodeRepository;
    private final UserRepository userRepository;
    private final SesUtil sesUtil;

    public void execute(SendAuthCodeWebRequest request) {
        if (request.getAuthCodeType() == AuthCodeType.SIGN_UP) {
            if (userRepository.existsByAccountId(request.getEmail())) {
                throw StudentAlreadyExistsException.EXCEPTION;
            }
        } else {
            if (!userRepository.existsByAccountId(request.getEmail())) {
                throw StudentNotFoundException.EXCEPTION;
            }
        }

        AuthCodeEntity authCodeEntity = AuthCodeEntity.builder()
                .code(StringUtil.generateRandomCode(6))
                .ttl(300)
                .isVerified(false)
                .email(request.getEmail())
                .build();
        authCodeRepository.save(authCodeEntity);

        sesUtil.sendMail(authCodeEntity.getCode(), authCodeEntity.getEmail());
    }
}
