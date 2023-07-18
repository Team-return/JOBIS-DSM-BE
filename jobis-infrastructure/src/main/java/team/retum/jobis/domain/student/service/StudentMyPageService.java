package team.retum.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.student.persistence.Student;
import team.retum.jobis.domain.student.presentation.dto.response.StudentMyPageResponse;
import team.retum.jobis.domain.persistence.facade.UserFacade;
import team.retum.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class StudentMyPageService {

    private final UserFacade userFacade;

    public StudentMyPageResponse execute() {
        Student student = userFacade.getCurrentStudent();

        return StudentMyPageResponse.of(student);
    }
}
