package team.returm.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.returm.jobis.domain.student.exception.StudentNotFoundException;
import team.returm.jobis.domain.auth.facade.AuthCodeFacade;
import team.returm.jobis.domain.student.facade.StudentFacade;
import team.returm.jobis.domain.student.presentation.dto.request.UpdatePasswordRequest;
import team.returm.jobis.domain.user.domain.User;
import team.returm.jobis.domain.user.domain.repository.UserRepository;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class UpdatePasswordService {

    private final AuthCodeFacade authCodeFacade;
    private final StudentFacade studentFacade;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void execute(UpdatePasswordRequest request) {

        if (!studentFacade.existsEmail(request.getEmail())) {
            throw StudentNotFoundException.EXCEPTION;
        }
        authCodeFacade.checkIsVerified(request.getEmail());

        User student = userRepository.queryUserByAccountId(request.getEmail())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        student.updatePassword(passwordEncoder.encode(request.getPassword()));
    }
}
