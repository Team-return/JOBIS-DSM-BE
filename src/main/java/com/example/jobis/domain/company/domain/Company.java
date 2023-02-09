package com.example.jobis.domain.company.domain;

import com.example.jobis.domain.company.domain.enums.CompanyType;
import com.example.jobis.domain.company.domain.type.Address;
import com.example.jobis.domain.company.domain.type.Manager;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@BatchSize(size = 200)
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Company {
    @Id
    @Column(name = "company_id")
    private UUID id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", columnDefinition = "BINARY(16)", nullable = false)
    private User user;

    @NotNull
    @Column(columnDefinition = "VARCHAR(50)")
    private String name;

    @NotNull
    @Column(columnDefinition = "VARCHAR(10)")
    private String bizNo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(13)")
    private CompanyType type;

    @Embedded
    private Address address;

    @NotNull
    @Column(columnDefinition = "VARCHAR(10)")
    private String representative;

    @NotNull
    @Column(columnDefinition = "DATE")
    private LocalDate foundedAt;

    @NotNull
    @Column(columnDefinition = "INTEGER")
    private int sales;

    @NotNull
    @Column(columnDefinition = "SMALLINT")
    private int workersCount;

    @Embedded
    private Manager manager;

    @Column(columnDefinition = "VARCHAR(12)")
    private String fax;

    @NotNull
    @Column(columnDefinition = "VARCHAR(30)")
    private String email;

    @NotNull
    private String companyIntroduce;

    @NotNull
    private String companyLogoUrl;



    @OneToMany(mappedBy = "company")
    private List<Recruitment> recruitmentList = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<CompanyAttachment> companyAttachments = new ArrayList<>();

    @Builder
    public Company(User user, String name, String mainAddress, String mainZipCode, String subAddress, String subZipCode,
                   String representative, LocalDate foundedAt, int sales, int workersCount, String managerName, String managerPhoneNo,
                   String subManagerName, String subManagerPhoneNo, String companyIntroduce, String companyLogoUrl,
                   String fax, String email, String bizNo) {
        this.user = user;
        this.name = name;
        this.type = CompanyType.PARTICIPATING;
        this.address = new Address(mainAddress, mainZipCode, subAddress, subZipCode);
        this.representative = representative;
        this.foundedAt = foundedAt;
        this.sales = sales;
        this.workersCount = workersCount;
        this.manager = new Manager(managerName, managerPhoneNo, subManagerName, subManagerPhoneNo);
        this.email = email;
        this.fax = fax;
        this.bizNo = bizNo;
        this.companyIntroduce = companyIntroduce;
        this.companyLogoUrl = companyLogoUrl;
    }

    public void update(String mainAddress, String mainZipCode, String subAddress, String subZipCode,
                       int sales, int workersCount, String managerName, String managerPhoneNo, String subManagerName,
                       String subManagerPhoneNo, String companyIntroduce, String companyLogoUrl, String fax, String email) {
        this.address.update(mainAddress, mainZipCode, subAddress, subZipCode);
        this.sales = sales;
        this.workersCount = workersCount;
        this.manager.update(managerName, managerPhoneNo, subManagerName, subManagerPhoneNo);
        this.companyIntroduce = companyIntroduce;
        this.companyLogoUrl = companyLogoUrl;
        this.fax = fax;
        this.email = email;
    }
}
