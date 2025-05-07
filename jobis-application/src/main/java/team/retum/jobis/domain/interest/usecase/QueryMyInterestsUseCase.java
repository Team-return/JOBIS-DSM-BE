package team.retum.jobis.domain.interest.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.interest.dto.response.InterestResponse;
import team.retum.jobis.domain.interest.dto.response.StudentQueryInterestsResponse;
import team.retum.jobis.domain.interest.spi.QueryInterestPort;
import team.retum.jobis.domain.student.model.Student;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryMyInterestsUseCase {

    private final QueryInterestPort queryInterestPort;
    private final SecurityPort securityPort;

    public StudentQueryInterestsResponse execute() {
        Student student = securityPort.getCurrentStudent();
        List<InterestResponse> interests = queryInterestPort.getByStudentId(student.getId());

        return StudentQueryInterestsResponse.of(student, interests);
    }
}
