package com.example.jobis.domain.company.domain.type;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    @Column(length = 5, nullable = false)
    private String mailNumber1;

    @Column(length = 100, nullable = false)
    private String address1;

    @Column(length = 5)
    private String mailNumber2;

    @Column(length = 100)
    private String address2;
}
