package team.retum.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.Service;

@RequiredArgsConstructor
@Service
public class UpdateStudentProfileService {

    private final UserFacade userFacade;

    public void execute(String profileImageUrl) {
        StudentEntity studentEntity = userFacade.getCurrentStudent();
        studentEntity.changeStudentProfile(profileImageUrl);
    }
}
