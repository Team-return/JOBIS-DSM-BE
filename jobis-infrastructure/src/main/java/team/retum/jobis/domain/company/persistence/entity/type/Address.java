package team.retum.jobis.domain.company.persistence.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    @NotNull
    @Column(columnDefinition = "VARCHAR(50)")
    private String mainAddress;

    @NotNull
    @Column(columnDefinition = "VARCHAR(50)")
    private String mainAddressDetail;

    @Column(columnDefinition = "VARCHAR(5)", nullable = false)
    private String mainZipCode;

    @Column(columnDefinition = "VARCHAR(50)")
    private String subAddress;

    @Column(columnDefinition = "VARCHAR(50)")
    private String subAddressDetail;

    @Column(columnDefinition = "VARCHAR(5)")
    private String subZipCode;
}
