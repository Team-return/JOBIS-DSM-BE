package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import com.example.jobisapplication.domain.code.model.CodeType;
import team.retum.jobis.domain.code.facade.CodeFacade;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import com.example.jobisapplication.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import com.example.jobisapplication.domain.recruitment.exception.RecruitmentAlreadyExistsException;
import team.retum.jobis.domain.recruitment.facade.RecruitmentFacade;
import team.retum.jobis.domain.recruitment.presentation.dto.request.ApplyRecruitmentWebRequest;
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

    public void execute(ApplyRecruitmentWebRequest request) {
        CompanyEntity companyEntity = userFacade.getCurrentCompany();

        if (recruitmentRepository.existsRecruitmentByCompanyAndStatusNot(companyEntity, RecruitStatus.DONE)) {
            throw RecruitmentAlreadyExistsException.EXCEPTION;
        }

        RecruitmentEntity recruitmentEntity = recruitmentRepository.saveRecruitment(
                RecruitmentEntity.builder()
                        .company(companyEntity)
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
                    Map<CodeType, List<CodeEntity>> codes = codeFacade
                            .queryCodesByIdIn(area.getCodes()).stream()
                            .collect(Collectors.groupingBy(CodeEntity::getCodeType));

                    recruitmentFacade.createRecruitArea(
                            codes,
                            recruitmentEntity,
                            area.getMajorTask(),
                            area.getHiring()
                    );
                });
    }
}
