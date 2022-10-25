package com.example.jobis.domain.company.domain.embedded;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Contact {

    @Column(length = 11)
    private String fax;

    @Column(length = 60, nullable = false)
    private String email;
}
