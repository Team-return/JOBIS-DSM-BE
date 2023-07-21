package team.retum.jobis.domain.company.persistence.repository.vo;

import com.example.jobisapplication.domain.company.spi.vo.CompanyDetailsVO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class QueryCompanyDetailsVO extends CompanyDetailsVO {

    @QueryProjection
    public QueryCompanyDetailsVO(String businessNumber, String companyName, String companyProfileUrl, String companyIntroduce,
                                 String zipCode1, String address1, String zipCode2, String address2,
                                 String manager1, String phoneNumber1, String manager2, String phoneNumber2,
                                 String fax, String email, String representativeName,
                                 LocalDate foundedAt, int workerNumber, double take,
                                 Long recruitmentId, String serviceName, String businessArea) {
        super(businessNumber, companyName, companyProfileUrl, companyIntroduce,
                zipCode1, address1, zipCode2, address2,
                manager1, phoneNumber1, manager2, phoneNumber2,
                fax, email, representativeName, foundedAt, workerNumber,
                take, recruitmentId, serviceName, businessArea);
    }
}
