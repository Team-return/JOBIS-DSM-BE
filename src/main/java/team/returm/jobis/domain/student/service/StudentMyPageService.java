package team.returm.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.student.presentation.dto.response.StudentMyPageResponse;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class StudentMyPageService {

    private final UserFacade userFacade;

    public StudentMyPageResponse execute() {
        Student student = userFacade.getCurrentStudent();

        return StudentMyPageResponse.builder()
                .studentName(student.getName())
                .studentGcn(Student.processGcn(
                        student.getGrade(),
                        student.getClassRoom(),
                        student.getNumber()
                ))
                .department(Student.getDepartment(
                        student.getGrade(),
                        student.getClassRoom()
                ))
                .build();
    }
}
