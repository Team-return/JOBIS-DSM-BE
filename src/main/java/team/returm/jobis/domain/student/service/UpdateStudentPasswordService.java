package team.returm.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.returm.jobis.domain.auth.domain.AuthCode;
import team.returm.jobis.domain.auth.domain.repository.AuthCodeRepository;
import team.returm.jobis.domain.auth.exception.UnverifiedEmailException;
import team.returm.jobis.domain.student.presentation.dto.request.UpdatePasswordRequest;
import team.returm.jobis.domain.user.domain.User;
import team.returm.jobis.domain.user.domain.repository.UserRepository;
import team.returm.jobis.domain.user.exception.UserNotFoundException;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class UpdateStudentPasswordService {

    private final AuthCodeRepository authCodeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void execute(UpdatePasswordRequest request) {

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
