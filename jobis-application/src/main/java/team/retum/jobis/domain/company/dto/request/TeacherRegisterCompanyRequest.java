package team.retum.jobis.domain.company.dto.request;

import lombok.Builder;

@Builder
public record TeacherRegisterCompanyRequest(

        String name,
        String businessNumber,
        String companyProfileUrl
) {

}
