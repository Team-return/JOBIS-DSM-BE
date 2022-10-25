package com.example.jobis.domain.company.domain.embedded;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Manager {

    @Column(length = 40, nullable = false)
    private String manager1;

    @Column(length = 11, nullable = false)
    private String phoneNumber1;

    @Column(length = 40, nullable = false)
    private String manager2;

    @Column(length = 11, nullable = false)
    private String phoneNumber2;
}
