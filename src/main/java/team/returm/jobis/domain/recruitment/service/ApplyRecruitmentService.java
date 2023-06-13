package team.returm.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.code.facade.CodeFacade;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.facade.RecruitmentFacade;
import team.returm.jobis.domain.recruitment.presentation.dto.request.ApplyRecruitmentRequest;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;
import team.returm.jobis.global.util.StringUtil;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ApplyRecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentFacade recruitmentFacade;
    private final CodeFacade codeFacade;
    private final UserFacade userFacade;

    public void execute(ApplyRecruitmentRequest request) {
        Company company = userFacade.getCurrentCompany();

        Recruitment recruitment = recruitmentRepository.saveRecruitment(
                Recruitment.builder()
                        .company(company)
                        .recruitYear(Year.now().getValue())
                        .militarySupport(request.isMilitarySupport())
                        .personalContact(request.isPersonalContact())
                        .workingHours(request.getWorkHours())
                        .preferentialTreatment(request.getPreferentialTreatment())
                        .requiredLicenses(request.getRequiredLicenses())
                        .status(RecruitStatus.REQUESTED)
                        .requiredGrade(request.getRequiredGrade())
                        .startDate(request.getStartDate())
                        .endDate(request.getEndDate())
                        .trainPay(request.getTrainPay())
                        .submitDocument(request.getSubmitDocument())
                        .pay(request.getPay())
                        .benefits(request.getBenefits())
                        .etc(request.getEtc())
                        .hiringProgress(request.getHiringProgress())
                        .build()
        );

        request.getAreas()
                .forEach(area -> {
                    Map<CodeType, List<Code>> codes = codeFacade
                            .queryCodesByIdIn(area.getCodes()).stream()
                            .collect(Collectors.groupingBy(Code::getCodeType));

                    recruitmentFacade.createRecruitArea(
                            codes,
                            recruitment,
                            area.getMajorTask(),
                            area.getHiring()
                    );
                });
    }
}
