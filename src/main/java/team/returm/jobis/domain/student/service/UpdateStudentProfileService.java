package team.returm.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class UpdateStudentProfileService {

    private final UserFacade userFacade;

    public void execute(String profileImageUrl) {
        Student student = userFacade.getCurrentStudent();
        student.changeStudentProfile(profileImageUrl);
    }
}
