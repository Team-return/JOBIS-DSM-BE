package com.example.jobis.domain.company.domain;

import com.example.jobis.domain.user.domain.User;
import com.example.jobis.global.enums.Authority;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Company extends User {

    @Column(length = 40, nullable = false)
    private String companyName;

    @Column(length = 10, nullable = false, unique = true)
    private String businessNumber;

    @Builder
    public Company(String companyName
            , String businessNumber, String password, String accountId) {
        super(accountId, password, Authority.COMPANY);
        this.companyName = companyName;
        this.businessNumber = businessNumber;
    }
}
