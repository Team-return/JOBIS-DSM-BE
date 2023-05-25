package team.returm.jobis.domain.company.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class QueryCompanyDetailsResponse {

    private final String businessNumber;
    private final String companyName;
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
    private final double take;
    private final Long recruitmentId;
    private final List<String> attachments;
}
