package team.returm.jobis.domain.recruitment.service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.domain.RecruitAreaCode;
import team.returm.jobis.domain.code.domain.SubCode;
import team.returm.jobis.domain.code.facade.CodeFacade;
import team.returm.jobis.domain.code.facade.SubCodeFacade;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.recruitment.domain.RecruitArea;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.facade.RecruitAreaFacade;
import team.returm.jobis.domain.recruitment.presentation.dto.request.ApplyRecruitmentRequest;
import team.returm.jobis.domain.recruitment.presentation.dto.request.ApplyRecruitmentRequest.Area;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;
import team.returm.jobis.global.util.StringUtil;

@RequiredArgsConstructor
@Service
public class ApplyRecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;
    private final CodeFacade codeFacade;
    private final SubCodeFacade subCodeFacade;
    private final RecruitAreaFacade recruitAreaFacade;

    public void execute(ApplyRecruitmentRequest request) {
        Company company = userFacade.getCurrentCompany();

        String hiringProgress = StringUtil.getHiringProgress(request.getHiringProgress());
        String requiredLicenses = StringUtil.getRequiredLicenses(request.getRequiredLicenses());

        Recruitment recruitment = recruitmentRepository.saveRecruitment(
                Recruitment.builder()
                        .company(company)
                        .recruitYear(Year.now().getValue())
                        .militarySupport(request.isMilitarySupport())
                        .workingHours(request.getWorkHours())
                        .preferentialTreatment(request.getPreferentialTreatment())
                        .requiredLicenses(requiredLicenses)
                        .status(RecruitStatus.REQUESTED)
                        .requiredGrade(request.getRequiredGrade())
                        .startDate(request.getStartDate())
                        .endDate(request.getEndDate())
                        .trainPay(request.getTrainPay())
                        .submitDocument(request.getSubmitDocument())
                        .pay(request.getPay())
                        .benefits(request.getBenefits())
                        .etc(request.getEtc())
                        .hiringProgress(hiringProgress)
                        .build()
        );

        List<RecruitAreaCode> recruitAreaCodes = new ArrayList<>();

        for (Area area : request.getAreas()) {
            RecruitArea recruitArea = recruitmentRepository.saveRecruitArea(
                    RecruitArea.builder()
                            .majorTask(area.getMajorTask())
                            .hiredCount(area.getHiring())
                            .recruitment(recruitment)
                            .build()
            );

            List<Code> codes = codeFacade.findAllCodeById(area.getJobCodes());
            List<SubCode> subCodes = subCodeFacade.findAllCodeById(area.getTechCodes());

            recruitmentRepository.saveAllRecruitAreaCodes(
                    recruitAreaFacade.addRecruitAreaCodes(recruitArea, codes, subCodes)
            );
        }
    }
}
