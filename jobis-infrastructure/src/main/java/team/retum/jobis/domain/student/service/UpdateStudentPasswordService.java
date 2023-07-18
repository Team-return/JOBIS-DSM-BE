package team.retum.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.retum.jobis.domain.student.presentation.dto.request.UpdatePasswordWebRequest;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import com.example.jobisapplication.domain.user.exception.InvalidPasswordException;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.Service;

@RequiredArgsConstructor
@Service
public class UpdateStudentPasswordService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    public void execute(UpdatePasswordWebRequest request) {
        UserEntity userEntity = userFacade.getCurrentUser();

        if (!passwordEncoder.matches(request.getCurrentPassword(), userEntity.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        userEntity.updatePassword(passwordEncoder.encode(request.getNewPassword()));
    }
}
