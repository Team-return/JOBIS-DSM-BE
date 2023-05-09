package team.returm.jobis.domain.company.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import team.returm.jobis.domain.acceptance.domain.Acceptance;
import team.returm.jobis.domain.company.domain.enums.CompanyType;
import team.returm.jobis.domain.company.domain.type.Address;
import team.returm.jobis.domain.company.domain.type.Manager;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.user.domain.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Company {
    @Id
    @Column(name = "company_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_id", nullable = false)
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
    @Column(columnDefinition = "VARCHAR(1000)")
    private String companyIntroduce;

    @NotNull
    @Column(columnDefinition = "VARCHAR(300)")
    private String companyLogoUrl;

    @NotNull
    @Column(columnDefinition = "VARCHAR(300)")
    private String bizRegistrationUrl;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    private String businessArea;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    private String serviceName;

    @OneToMany(mappedBy = "company")
    private final List<Recruitment> recruitmentList = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private final List<CompanyAttachment> companyAttachments = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private final List<Acceptance> acceptances = new ArrayList<>();

    @Builder
    public Company(User user, String name, String mainAddress, String mainZipCode, String subAddress, String subZipCode,
                   String representative, LocalDate foundedAt, int sales, int workersCount, String managerName, String managerPhoneNo,
                   String subManagerName, String subManagerPhoneNo, String companyIntroduce, String companyLogoUrl,
                   String fax, String email, String bizNo, String bizRegistrationUrl, String businessArea, String serviceName) {
        this.user = user;
        this.name = name;
        this.bizRegistrationUrl = bizRegistrationUrl;
        this.businessArea = businessArea;
        this.serviceName = serviceName;
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

    public Company changeCompanyType(CompanyType type) {
        this.type = type;
        return this;
    }
}
