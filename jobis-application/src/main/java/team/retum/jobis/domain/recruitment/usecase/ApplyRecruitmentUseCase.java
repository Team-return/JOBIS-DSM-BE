package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.recruitment.dto.request.ApplyRecruitmentRequest;
import team.retum.jobis.domain.recruitment.exception.RecruitmentAlreadyExistsException;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.service.SaveRecruitmentAreaService;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

import java.time.Year;

@RequiredArgsConstructor
@UseCase
public class ApplyRecruitmentUseCase {

    private final CommandRecruitmentPort commandRecruitmentPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final SecurityPort securityPort;
    private final SaveRecruitmentAreaService saveRecruitmentAreaService;

    public void execute(ApplyRecruitmentRequest request) {
        Long currentCompanyId = securityPort.getCurrentUserId();
        checkRecruitmentExists(currentCompanyId);

        Recruitment recruitment = commandRecruitmentPort.saveRecruitment(
                Recruitment.builder()
                        .companyId(currentCompanyId)
                        .recruitYear(Year.now().getValue())
                        .militarySupport(request.isMilitarySupport())
                        .personalContract(request.isPersonalContact())
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

        request.getAreas().forEach(area -> saveRecruitmentAreaService.execute(area, recruitment.getId()));
    }

    private void checkRecruitmentExists(Long companyId) {
        if (queryRecruitmentPort.existsOnRecruitmentByCompanyId(companyId)) {
            throw RecruitmentAlreadyExistsException.EXCEPTION;
        }
    }
}
