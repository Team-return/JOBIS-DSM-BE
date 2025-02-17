package team.retum.jobis.domain.interest.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.FeignClientPort;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.interest.spi.InterestPort;
import team.retum.jobis.domain.recruitment.dto.response.StudentQueryRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.dto.response.StudentQueryRecruitmentsResponse.StudentRecruitmentResponse;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.student.model.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryMyInterestRecruitmentUseCase {

    private final SecurityPort securityPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final FeignClientPort feignClientPort;
    private final InterestPort interestPort;

    public StudentQueryRecruitmentsResponse execute() {
        Student student = securityPort.getCurrentStudent();

        Map<CodeType, List<String>> interests = getInterestsByCodeTypes(student.getId());

        List<String> major = interests.get(CodeType.JOB);
        List<String> tech = interests.get(CodeType.TECH);

        List<String> interestCompanies = feignClientPort.getMyInterestCompanyByMajorAndTech(major, tech);

        List<StudentRecruitmentResponse> recruitments =
            queryRecruitmentPort.getStudentRecruitmentByCompanyName(interestCompanies, student.getId()).stream()
            .map(StudentRecruitmentResponse::from)
            .toList();

        return new StudentQueryRecruitmentsResponse(recruitments);
    }

    private Map<CodeType, List<String>> getInterestsByCodeTypes(Long studentId) {
        List<CodeType> types = List.of();
        return types.stream()
            .collect(Collectors.toMap(
                type -> type,
                type -> interestPort.getAllByStudentIdAndCodeType(studentId, type)
            ));
    }
}
