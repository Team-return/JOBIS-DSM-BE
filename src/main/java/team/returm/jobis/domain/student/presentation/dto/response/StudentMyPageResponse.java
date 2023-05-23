package team.returm.jobis.domain.student.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.application.domain.repository.vo.QueryApplyCompaniesVO;

import java.util.List;

@Getter
@Builder
public class StudentMyPageResponse {

    private final String studentName;
    private final String studentGcn;
    private final String department;
}
