package team.returm.jobis.domain.student.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.application.domain.repository.vo.QueryApplyCompaniesVO;

import java.util.List;

@Getter
@Builder
public class StudentMainPageResponse {

    private final String studentName;
    private final String studentGcn;
    private final List<QueryApplyCompaniesVO> applyCompanies;
    private final Long totalStudentCount;
    private final Long passCount;
    private final Long approvedCount;
}
