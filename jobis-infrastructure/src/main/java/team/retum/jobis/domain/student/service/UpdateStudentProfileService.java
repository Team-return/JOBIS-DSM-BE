package team.retum.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.student.domain.Student;
import team.retum.jobis.domain.user.facade.UserFacade;
import team.retum.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class UpdateStudentProfileService {

    private final UserFacade userFacade;

    public void execute(String profileImageUrl) {
        Student student = userFacade.getCurrentStudent();
        student.changeStudentProfile(profileImageUrl);
    }
}
