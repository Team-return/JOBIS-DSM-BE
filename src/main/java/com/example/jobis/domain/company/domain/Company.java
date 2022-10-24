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

    @Column(length = 40, nullable = false)
    private String representativeName;

    @Column(length = 5, nullable = false)
    private String mailNumber1;

    @Column(length = 100, nullable = false)
    private String address1;

    @Column(length = 5)
    private String mailNumber2;

    @Column(length = 100)
    private String address2;

    @Column(length = 8, nullable = false)
    private String foundedAt;

    @Column(nullable = false)
    private Long workerNumber;

    @Column(nullable = false)
    private Long take;

    @Column(length = 40, nullable = false)
    private String manager1;

    @Column(length = 11, nullable = false)
    private String phoneNumber1;

    @Column(length = 40, nullable = false)
    private String manager2;

    @Column(length = 11, nullable = false)
    private String phoneNumber2;

    @Column(length = 11)
    private String fax;

    @Column(length = 60, nullable = false)
    private String email;

    @Column(length = 4000, nullable = false)
    private String companyIntroduce;

    @Builder
    public Company(String companyName, String businessNumber, String password, String representativeName, String mailNumber1, String address1, String mailNumber2, String address2, String foundedAt, Long workerNumber, Long take, String manager1, String phoneNumber1, String manager2, String phoneNumber2, String fax, String email, String companyIntroduce) {
        this.companyName = companyName;
        this.businessNumber = businessNumber;
        this.password = password;
        this.representativeName = representativeName;
        this.mailNumber1 = mailNumber1;
        this.address1 = address1;
        this.mailNumber2 = mailNumber2;
        this.address2 = address2;
        this.foundedAt = foundedAt;
        this.workerNumber = workerNumber;
        this.take = take;
        this.manager1 = manager1;
        this.phoneNumber1 = phoneNumber1;
        this.manager2 = manager2;
        this.phoneNumber2 = phoneNumber2;
        this.fax = fax;
        this.email = email;
        this.companyIntroduce = companyIntroduce;
    }
}
