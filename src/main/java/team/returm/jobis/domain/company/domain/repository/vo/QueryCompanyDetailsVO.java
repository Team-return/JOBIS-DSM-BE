package team.returm.jobis.domain.company.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class QueryCompanyDetailsVO {

    private final String businessNumber;
    private final String companyProfileUrl;
    private final String companyIntroduce;
    private final String zipCode1;
    private final String address1;
    private final String zipCode2;
    private final String address2;
    private final String manager1;
    private final String phoneNumber1;
    private final String manager2;
    private final String phoneNumber2;
    private final String fax;
    private final String email;
    private final String representativeName;
    private final LocalDate foundedAt;
    private final int workerNumber;
    private final int take;
    private final Long recruitmentId;

    @QueryProjection
    public QueryCompanyDetailsVO(String businessNumber, String companyProfileUrl, String companyIntroduce,
                                 String zipCode1, String address1, String zipCode2, String address2,
                                 String manager1, String phoneNumber1, String manager2, String phoneNumber2,
                                 String fax, String email, String representativeName,
                                 LocalDate foundedAt, int workerNumber, int take, Long recruitmentId) {
        this.businessNumber = businessNumber;
        this.companyProfileUrl = companyProfileUrl;
        this.companyIntroduce = companyIntroduce;
        this.zipCode1 = zipCode1;
        this.address1 = address1;
        this.zipCode2 = zipCode2;
        this.address2 = address2;
        this.manager1 = manager1;
        this.phoneNumber1 = phoneNumber1;
        this.manager2 = manager2;
        this.phoneNumber2 = phoneNumber2;
        this.fax = fax;
        this.email = email;
        this.representativeName = representativeName;
        this.foundedAt = foundedAt;
        this.workerNumber = workerNumber;
        this.take = take;
        this.recruitmentId = recruitmentId;
    }
}
