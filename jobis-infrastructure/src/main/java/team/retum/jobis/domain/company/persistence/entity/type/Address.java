package team.retum.jobis.domain.company.persistence.entity.type;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    @Column(nullable = false)
    private String mainAddress;

    @Column(columnDefinition = "VARCHAR(5)", nullable = false)
    private String mainZipCode;

    private String subAddress;

    @Column(columnDefinition = "VARCHAR(5)")
    private String subZipCode;


    public void update(String mainAddress, String mainZipCode, String subAddress, String subZipCode) {
        this.mainAddress = mainAddress;
        this.mainZipCode = mainZipCode;
        this.subAddress = subAddress;
        this.subZipCode = subZipCode;
    }
}
