package com.example.jobis.domain.company.domain.type;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    @NotNull
    private String mainAddress;

    @NotNull
    @Column(columnDefinition = "VARCHAR(5)")
    private String mainZipCode;

    @NotNull
    private String subAddress;

    @NotNull
    @Column(columnDefinition = "VARCHAR(5)")
    private String subZipCode;


    public void update(String mainAddress, String mainZipCode, String subAddress, String subZipCode) {
        this.mainAddress = mainAddress;
        this.mainZipCode = mainZipCode;
        this.subAddress = subAddress;
        this.subZipCode = subZipCode;
    }
}
