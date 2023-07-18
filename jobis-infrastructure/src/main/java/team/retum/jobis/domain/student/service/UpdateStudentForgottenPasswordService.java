package team.retum.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.retum.jobis.domain.auth.persistence.AuthCode;
import team.retum.jobis.domain.auth.persistence.repository.AuthCodeRepository;
import team.retum.jobis.domain.auth.exception.UnverifiedEmailException;
import team.retum.jobis.domain.student.presentation.dto.request.UpdateForgottenPasswordRequest;
import team.retum.jobis.domain.user.persistence.User;
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

        AuthCode authCode = authCodeRepository.findById(request.getEmail())
                .orElseThrow(() -> UnverifiedEmailException.EXCEPTION);
        authCode.checkIsVerified();

        User user = userRepository.queryUserByAccountId(request.getEmail())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        user.updatePassword(passwordEncoder.encode(request.getPassword()));
    }
}
