package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.code.persistence.Code;
import team.retum.jobis.domain.code.persistence.enums.CodeType;
import team.retum.jobis.domain.code.facade.CodeFacade;
import team.retum.jobis.domain.company.persistence.Company;
import team.retum.jobis.domain.recruitment.persistence.Recruitment;
import team.retum.jobis.domain.recruitment.persistence.enums.RecruitStatus;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.exception.RecruitmentAlreadyExistsException;
import team.retum.jobis.domain.recruitment.facade.RecruitmentFacade;
import team.retum.jobis.domain.recruitment.presentation.dto.request.ApplyRecruitmentRequest;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.Service;

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

        if (recruitmentRepository.existsRecruitmentByCompanyAndStatusNot(company, RecruitStatus.DONE)) {
            throw RecruitmentAlreadyExistsException.EXCEPTION;
        }

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
