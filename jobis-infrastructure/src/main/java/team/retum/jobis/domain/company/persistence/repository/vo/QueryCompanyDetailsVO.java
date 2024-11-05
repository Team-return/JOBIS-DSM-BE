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
                                 String managerName, String managerPhoneNo, String email, String representativeName, String representativePhoneNo,
                                 LocalDate foundedAt, int workerNumber, double take,
                                 Long recruitmentId, String serviceName, Long businessAreaCode,
                                 String businessArea, List<String> attachmentUrls, String bizRegistrationUrl, boolean headquarter) {
        super(businessNumber, companyName, companyProfileUrl, companyIntroduce,
            mainZipCode, mainAddress, mainAddressDetail, managerName, managerPhoneNo, email,
            representativeName, representativePhoneNo, foundedAt, workerNumber, take, recruitmentId,
            serviceName, businessAreaCode, businessArea, attachmentUrls, bizRegistrationUrl, headquarter);
    }
}
