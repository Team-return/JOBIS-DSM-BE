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
public class Manager {

    @Column(length = 40, nullable = false)
    private String manager1;

    @Column(length = 11, nullable = false)
    private String phoneNumber1;

    @Column(length = 40, nullable = false)
    private String manager2;

    @Column(length = 11, nullable = false)
    private String phoneNumber2;

    @Builder
    public Manager(String manager1, String phoneNumber1, String manager2, String phoneNumber2) {
        this.manager1 = manager1;
        this.phoneNumber1 = phoneNumber1;
        this.manager2 = manager2;
        this.phoneNumber2 = phoneNumber2;
    }
}
