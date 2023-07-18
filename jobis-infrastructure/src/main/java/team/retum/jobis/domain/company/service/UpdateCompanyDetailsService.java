package team.retum.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.presentation.dto.request.UpdateCompanyDetailsRequest;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.Service;

@RequiredArgsConstructor
@Service
public class UpdateCompanyDetailsService {
    private final UserFacade userFacade;

    public void execute(UpdateCompanyDetailsRequest request) {
        CompanyEntity companyEntity = userFacade.getCurrentCompany();

        companyEntity.update(
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
