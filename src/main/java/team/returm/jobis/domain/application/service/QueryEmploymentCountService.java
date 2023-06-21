package team.returm.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.domain.repository.vo.QueryTotalApplicationCountVO;
import team.returm.jobis.domain.application.presentation.dto.response.QueryEmploymentCountResponse;
import team.returm.jobis.domain.student.domain.repository.StudentRepository;
import team.returm.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryEmploymentCountService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;

    public QueryEmploymentCountResponse execute() {
        Long totalStudentCount = studentRepository.countStudentByGrade(3);
        QueryTotalApplicationCountVO counts = applicationRepository.queryTotalApplicationCount();

        return QueryEmploymentCountResponse.of(
                totalStudentCount,
                counts.getPassedCount(),
                counts.getApprovedCount()
        );
    }
}
