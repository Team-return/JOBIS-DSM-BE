package com.example.jobis.domain.company.domain.type;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    @Column(length = 5, nullable = false)
    private String zipCode1;

    @Column(length = 100, nullable = false)
    private String address1;

    @Column(length = 5)
    private String zipCode2;

    @Column(length = 100)
    private String address2;

    @Builder
    public Address(String zipCode1, String address1, String zipCode2, String address2) {
        this.zipCode1 = zipCode1;
        this.address1 = address1;
        this.zipCode2 = zipCode2;
        this.address2 = address2;
    }
}
