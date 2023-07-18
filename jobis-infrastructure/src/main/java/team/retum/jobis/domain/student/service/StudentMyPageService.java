package team.retum.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.presentation.dto.response.StudentMyPageResponse;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class StudentMyPageService {

    private final UserFacade userFacade;

    public StudentMyPageResponse execute() {
        StudentEntity studentEntity = userFacade.getCurrentStudent();

        return StudentMyPageResponse.of(studentEntity);
    }
}
