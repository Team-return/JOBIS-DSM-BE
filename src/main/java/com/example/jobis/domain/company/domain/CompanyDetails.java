package com.example.jobis.domain.company.domain;

import com.example.jobis.domain.company.domain.type.Address;
import com.example.jobis.domain.company.domain.type.CompanyInfo;
import com.example.jobis.domain.company.domain.type.Contact;
import com.example.jobis.domain.company.domain.type.Manager;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CompanyDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(length = 4000, nullable = false)
    private String companyIntroduce;

    @Embedded
    private Address address;

    @Embedded
    private Manager manager;

    @Embedded
    private Contact contact;

    @Embedded
    private CompanyInfo companyInfo;

    @Builder
    public CompanyDetails(Company company, String companyIntroduce, Address address, Manager manager, Contact contact, CompanyInfo companyInfo) {
        this.company = company;
        this.companyIntroduce = companyIntroduce;
        this.address = address;
        this.manager = manager;
        this.contact = contact;
        this.companyInfo = companyInfo;
    }
}
