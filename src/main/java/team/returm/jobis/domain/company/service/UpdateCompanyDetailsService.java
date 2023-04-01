package team.returm.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.company.presentation.dto.request.UpdateCompanyDetailsRequest;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class UpdateCompanyDetailsService {
    private final UserFacade userFacade;

    public void execute(UpdateCompanyDetailsRequest request) {
        Company company = userFacade.getCurrentCompany();

        company.update(
                request.getAddress1(), request.getZipCode1(),
                request.getAddress2(), request.getZipCode2(),
                request.getTake(), request.getWorkerNumber(),
                request.getManager1(), request.getPhoneNumber1(),
                request.getManager2(), request.getPhoneNumber2(),
                request.getCompanyIntroduce(), request.getCompanyProfileUrl(),
                request.getFax(), request.getEmail()
        );
    }
}
