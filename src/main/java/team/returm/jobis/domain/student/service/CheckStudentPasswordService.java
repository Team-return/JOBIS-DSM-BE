package team.returm.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.returm.jobis.domain.user.domain.User;
import team.returm.jobis.domain.user.exception.InvalidPasswordException;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class CheckStudentPasswordService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    public void execute(String password) {
        User user = userFacade.getCurrentUser();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }
    }
}
