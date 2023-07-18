package team.retum.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.retum.jobis.domain.auth.persistence.entity.AuthCodeEntity;
import team.retum.jobis.domain.auth.persistence.repository.AuthCodeRepository;
import team.retum.jobis.domain.auth.exception.UnverifiedEmailException;
import team.retum.jobis.domain.student.presentation.dto.request.UpdateForgottenPasswordRequest;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import team.retum.jobis.domain.user.persistence.repository.UserRepository;
import team.retum.jobis.domain.user.exception.UserNotFoundException;
import com.example.jobisapplication.common.annotation.Service;

@RequiredArgsConstructor
@Service
public class UpdateStudentForgottenPasswordService {

    private final AuthCodeRepository authCodeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void execute(UpdateForgottenPasswordRequest request) {

        if (!userRepository.existsByAccountId(request.getEmail())) {
            throw UserNotFoundException.EXCEPTION;
        }

        AuthCodeEntity authCodeEntity = authCodeRepository.findById(request.getEmail())
                .orElseThrow(() -> UnverifiedEmailException.EXCEPTION);
        authCodeEntity.checkIsVerified();

        UserEntity userEntity = userRepository.queryUserByAccountId(request.getEmail())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        userEntity.updatePassword(passwordEncoder.encode(request.getPassword()));
    }
}
