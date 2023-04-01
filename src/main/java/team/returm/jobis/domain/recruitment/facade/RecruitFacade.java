package team.returm.jobis.domain.recruitment.facade;

import java.util.List;
import team.returm.jobis.domain.code.domain.RecruitAreaCode;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentJpaRepository;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import team.returm.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.returm.jobis.domain.recruitment.presentation.dto.response.RecruitAreaResponse;

@RequiredArgsConstructor
@Component
public class RecruitFacade {

    private final RecruitmentJpaRepository recruitmentJpaRepository;
    private final RecruitmentRepository recruitmentRepository;

    public Recruitment queryRecruitmentById(Long id) {
        return recruitmentJpaRepository.findById(id)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
    }

    public Recruitment getLatestRecruitByCompany(Company company) {
        return company.getRecruitmentList().get(company.getRecruitmentList().size() - 1);
    }

    public List<RecruitAreaResponse> queryRecruitAreas(Long recruitmentId) {
        return recruitmentRepository.queryRecruitAreasByRecruitmentId(recruitmentId).stream()
                .map(recruitArea ->
                        RecruitAreaResponse.builder()
                                .recruitAreaId(recruitArea.getId())
                                .majorTask(recruitArea.getMajorTask())
                                .hiring(recruitArea.getHiredCount())
                                .tech(
                                        recruitArea.getCodeList().stream()
                                                .filter(recruitAreaCode -> recruitAreaCode.getCodeType() == CodeType.TECH)
                                                .map(RecruitAreaCode::getCodeKeyword).toList()
                                )
                                .job(
                                        recruitArea.getCodeList().stream()
                                                .filter(recruitAreaCode -> recruitAreaCode.getCodeType() == CodeType.JOB)
                                                .map(RecruitAreaCode::getCodeKeyword).toList()
                                )
                                .build()
                ).toList();
    }
}
