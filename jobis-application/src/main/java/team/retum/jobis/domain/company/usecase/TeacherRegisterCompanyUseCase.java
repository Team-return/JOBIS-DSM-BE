package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.FeignClientPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.company.dto.request.TeacherRegisterCompanyRequest;
import team.retum.jobis.domain.company.exception.CompanyAlreadyExistsException;
import team.retum.jobis.domain.company.exception.CompanyNotExistsException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.CommandCompanyPort;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.recruitment.exception.RecruitmentAlreadyExistsException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitAreaPort;
import team.retum.jobis.domain.recruitment.spi.RecruitmentPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.CommandUserPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class TeacherRegisterCompanyUseCase {

    private final FeignClientPort feignClientPort;
    private final QueryCompanyPort queryCompanyPort;
    private final CommandCompanyPort commandCompanyPort;
    private final QueryCodePort queryCodePort;
    private final CommandUserPort commandUserPort;
    private final RecruitmentPort recruitmentPort;
    private final CommandRecruitAreaPort commandRecruitAreaPort;

    private static final long BUSINESS_AREA_CODE = 1;

    public void execute(TeacherRegisterCompanyRequest request) {
        checkCompanyExists(request.businessNumber());

        Code code = queryCodePort.getByIdOrThrow(BUSINESS_AREA_CODE);

        User user = commandUserPort.save(
                User.builder()
                        .accountId(request.businessNumber())
                        .authority(Authority.COMPANY)
                        .token(null)
                        .build()
        );

        Company company = commandCompanyPort.save(
                Company.of(user.getId(), request, code.getKeyword())
        );

        checkRecruitmentApplicable(company);
        Recruitment recruitment = recruitmentPort.save(Recruitment.of(company.getId()));
        commandRecruitAreaPort.saveAll(List.of(RecruitArea.of(recruitment.getId())));

    }

    private void checkCompanyExists(String businessNumber) {
        if (!feignClientPort.checkCompanyExistsByBizNo(businessNumber)) {
            throw CompanyNotExistsException.EXCEPTION;
        }
    }

    private void checkRecruitmentApplicable(Company company) {
        if (recruitmentPort.existsByCompanyIdAndWinterIntern(company.getId(), false)) {
            throw RecruitmentAlreadyExistsException.EXCEPTION;
        }
    }
}