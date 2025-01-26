package team.retum.jobis.domain.interest.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.FeignClientPort;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.spi.CodePort;
import team.retum.jobis.domain.recruitment.dto.RecruitmentFilter;
import team.retum.jobis.domain.recruitment.dto.response.StudentQueryRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.dto.response.StudentQueryRecruitmentsResponse.StudentRecruitmentResponse;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.student.model.Student;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryMyInterestRecruitmentUseCase {

    private final SecurityPort securityPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final FeignClientPort feignClientPort;
    private final CodePort codePort;

    public StudentQueryRecruitmentsResponse execute() {

        Student student = securityPort.getCurrentStudent();

        List<String> major = codePort.getAllByStudentAndCodeType(student, CodeType.JOB);
        List<String> tech = codePort.getAllByStudentAndCodeType(student, CodeType.TECH);

        List<String> response = feignClientPort.getMyInterestRecruitmentByMajorAndTech(major, tech);
        
        List<StudentRecruitmentResponse> recruitments = response.stream()
            .map(companyName -> queryRecruitmentPort.getStudentRecruitmentByCompanyName(companyName, student.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(StudentRecruitmentResponse::from)
            .toList();

        return new StudentQueryRecruitmentsResponse(recruitments);
    }
}
