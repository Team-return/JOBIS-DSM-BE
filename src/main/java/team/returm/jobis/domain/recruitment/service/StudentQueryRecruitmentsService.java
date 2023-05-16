package team.returm.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.facade.CodeFacade;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.presentation.dto.response.StudentQueryRecruitmentsResponse;
import team.returm.jobis.domain.recruitment.presentation.dto.response.StudentQueryRecruitmentsResponse.StudentRecruitmentResponse;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.ReadOnlyService;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class StudentQueryRecruitmentsService {

    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;
    private final CodeFacade codeFacade;

    public StudentQueryRecruitmentsResponse execute(String name, Integer page, List<String> keywords) {
        Long studentId = userFacade.getCurrentUserId();
        List<Code> codes = codeFacade.queryCodeByKeywordIn(keywords);

        List<StudentRecruitmentResponse> recruitments =
                recruitmentRepository.queryRecruitmentsByConditions(
                                Year.now().getValue(), null, null,
                                RecruitStatus.RECRUITING, name, page - 1, codes, studentId
                        ).stream()
                        .map(
                                r -> StudentRecruitmentResponse.builder()
                                        .recruitId(r.getRecruitment().getId())
                                        .companyName(r.getCompany().getName())
                                        .trainPay(r.getRecruitment().getPayInfo().getTrainingPay())
                                        .jobCodeList(r.getRecruitAreaList())
                                        .military(r.getRecruitment().getMilitarySupport())
                                        .companyProfileUrl(r.getCompany().getCompanyLogoUrl())
                                        .totalHiring(r.getTotalHiring())
                                        .isBookmarked(r.getIsBookmarked() != 0)
                                        .build()
                        ).toList();

        return new StudentQueryRecruitmentsResponse(recruitments);
    }
}
