package team.retum.jobis.domain.company.model;

import lombok.Builder;

@Builder
public record AddressInfo(
    String mainAddress,
    String mainAddressDetail,
    String mainZipCode,
    String subAddress,
    String subAddressDetail,
    String subZipCode
) {

}
