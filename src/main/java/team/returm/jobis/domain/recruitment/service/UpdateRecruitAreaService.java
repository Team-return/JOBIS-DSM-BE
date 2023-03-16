package team.returm.jobis.domain.recruitment.service;

import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.facade.CodeFacade;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.company.facade.CompanyFacade;
import team.returm.jobis.domain.recruitment.presentation.dto.request.UpdateRecruitAreaRequest;
import team.returm.jobis.domain.recruitment.domain.RecruitArea;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.facade.RecruitAreaFacade;
import team.returm.jobis.domain.user.domain.User;
import team.returm.jobis.domain.user.domain.enums.Authority;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class UpdateRecruitAreaService {

    private final RecruitAreaFacade recruitAreaFacade;
    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;
    private final CodeFacade codeFacade;
    private final CompanyFacade companyFacade;

    public void execute(UpdateRecruitAreaRequest request, UUID recruitAreaId) {
        User user = userFacade.getCurrentUser();

        RecruitArea recruitArea = recruitAreaFacade.getRecruitAreaById(recruitAreaId);

        if (user.getAuthority() == Authority.COMPANY) {
            Company company = companyFacade.queryCompanyById(user.getId());
            recruitArea.getRecruitment().checkCompany(company.getId());
        }

        recruitmentRepository.deleteRecruitAreaCodeByRecruitAreaId(recruitArea.getId());
        List<Code> codes = codeFacade.findAllCodeById(
                Stream.of(request.getJobCodes(), request.getTechCodes())
                        .flatMap(Collection::stream)
                        .toList()
        );

        recruitArea.update(request.getHiring(), request.getMajorTask());
        recruitmentRepository.saveAllRecruitAreaCodes(
                codeFacade.generateRecruitAreaCode(recruitArea, codes)
        );
    }
}
