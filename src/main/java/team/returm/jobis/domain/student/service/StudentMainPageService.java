package team.returm.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.domain.repository.vo.QueryApplyCompaniesVO;
import team.returm.jobis.domain.application.domain.repository.vo.QueryTotalApplicationCountVO;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.student.presentation.dto.response.StudentMainPageResponse;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.ReadOnlyService;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class StudentMainPageService {

    private final ApplicationRepository applicationRepository;
    private final UserFacade userFacade;

    public StudentMainPageResponse execute() {
        Student student = userFacade.getCurrentStudent();
        QueryTotalApplicationCountVO counts = applicationRepository.queryTotalApplicationCount();
        List<QueryApplyCompaniesVO> applyCompanies = applicationRepository.queryApplyCompanyNames(student.getId());


        return StudentMainPageResponse.builder()
                .studentName(student.getName())
                .studentGcn(Student.processGcn(
                        student.getGrade(),
                        student.getClassRoom(),
                        student.getNumber()
                ))
                .applyCompanies(applyCompanies)
                .totalStudentCount(counts.getTotalStudentCount())
                .passCount(counts.getPassCount())
                .approvedCount(counts.getApprovedCount())
                .build();
    }
}
