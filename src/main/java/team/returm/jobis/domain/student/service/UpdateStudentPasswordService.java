package team.returm.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.returm.jobis.domain.student.exception.StudentNotFoundException;
import team.returm.jobis.domain.auth.facade.AuthCodeFacade;
import team.returm.jobis.domain.student.presentation.dto.request.UpdatePasswordRequest;
import team.returm.jobis.domain.user.domain.User;
import team.returm.jobis.domain.user.domain.repository.UserRepository;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class UpdateStudentPasswordService {

    private final AuthCodeFacade authCodeFacade;
    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void execute(UpdatePasswordRequest request) {

        if (!userFacade.existsAccountId(request.getEmail())) {
            throw StudentNotFoundException.EXCEPTION;
        }
        authCodeFacade.checkIsVerified(request.getEmail());

        User user = userRepository.queryUserByAccountId(request.getEmail())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        user.updatePassword(passwordEncoder.encode(request.getPassword()));
    }
}
