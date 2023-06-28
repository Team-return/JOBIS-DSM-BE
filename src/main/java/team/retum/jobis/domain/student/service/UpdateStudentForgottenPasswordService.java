package team.retum.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.retum.jobis.domain.auth.domain.AuthCode;
import team.retum.jobis.domain.auth.domain.repository.AuthCodeRepository;
import team.retum.jobis.domain.auth.exception.UnverifiedEmailException;
import team.retum.jobis.domain.student.presentation.dto.request.UpdateForgottenPasswordRequest;
import team.retum.jobis.domain.user.domain.User;
import team.retum.jobis.domain.user.domain.repository.UserRepository;
import team.retum.jobis.domain.user.exception.UserNotFoundException;
import team.retum.jobis.global.annotation.Service;

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
