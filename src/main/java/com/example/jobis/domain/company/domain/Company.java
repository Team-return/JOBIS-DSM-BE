package com.example.jobis.domain.company.domain;

import com.example.jobis.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Company extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(length = 40, nullable = false)
    private String companyName;

    @Column(length = 10, nullable = false, unique = true)
    private String businessNumber;

    @Column(nullable = false)
    private String password;

    @Builder
    public Company(String companyName, String businessNumber, String password) {
        this.companyName = companyName;
        this.businessNumber = businessNumber;
        this.password = password;
    }
}
