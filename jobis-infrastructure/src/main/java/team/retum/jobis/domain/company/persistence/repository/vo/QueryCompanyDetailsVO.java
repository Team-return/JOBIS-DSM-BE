package team.retum.jobis.domain.company.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.company.spi.vo.CompanyDetailsVO;

import java.time.LocalDate;
import java.util.List;

@Getter
public class QueryCompanyDetailsVO extends CompanyDetailsVO {

    @QueryProjection
    public QueryCompanyDetailsVO(String businessNumber, String companyName, String companyProfileUrl, String companyIntroduce,
                                 String mainZipCode, String mainAddress, String mainAddressDetail,
                                 String subZipCode, String subAddress, String subAddressDetail,
                                 String managerName, String managerPhoneNo, String subManagerName, String subManagerPhoneNo,
                                 String fax, String email, String representativeName, String representativePhoneNo,
                                 LocalDate foundedAt, int workerNumber, double take,
                                 Long recruitmentId, String serviceName, String businessArea, List<String> attachmentUrls) {
        super(businessNumber, companyName, companyProfileUrl, companyIntroduce,
                mainZipCode, mainAddress, mainAddressDetail, subZipCode, subAddress, subAddressDetail,
                managerName, managerPhoneNo, subManagerName, subManagerPhoneNo,
                fax, email, representativeName, representativePhoneNo,
                foundedAt, workerNumber, take, recruitmentId,
                serviceName, businessArea, attachmentUrls);
    }
}
