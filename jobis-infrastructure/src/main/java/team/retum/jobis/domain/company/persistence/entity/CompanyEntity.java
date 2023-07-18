package team.retum.jobis.domain.company.persistence.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import team.retum.jobis.domain.acceptance.persistence.entity.AcceptanceEntity;
import com.example.jobisapplication.domain.company.domain.CompanyType;
import team.retum.jobis.domain.company.persistence.type.Address;
import team.retum.jobis.domain.company.persistence.type.Manager;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import team.retum.jobis.global.util.ImageProperty;

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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_company")
@Entity
public class CompanyEntity {

    @Id
    @Column(name = "company_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_id", nullable = false)
    private UserEntity userEntity;

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

    @NotNull
    @Column(columnDefinition = "BIT(1)")
    private Boolean isMou;

    @Embedded
    private Address address;

    @NotNull
    @Column(columnDefinition = "VARCHAR(10)")
    private String representative;

    @NotNull
    @Column(columnDefinition = "DATE")
    private LocalDate foundedAt;

    @NotNull
    @Column(columnDefinition = "DOUBLE")
    private double take;

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
    @Column(columnDefinition = "VARCHAR(4000)")
    private String companyIntroduce;

    @ColumnDefault(ImageProperty.DEFAULT_COMPANY_LOGO_IMAGE)
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
    private final List<RecruitmentEntity> recruitmentEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private final List<CompanyAttachmentEntity> companyAttachmentEntities = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private final List<AcceptanceEntity> acceptanceEntities = new ArrayList<>();

    @Builder
    public CompanyEntity(UserEntity userEntity, String name, String mainAddress, String mainZipCode, String subAddress, String subZipCode,
                         String representative, LocalDate foundedAt, double take, int workersCount, String managerName, String managerPhoneNo,
                         String subManagerName, String subManagerPhoneNo, String companyIntroduce, String companyLogoUrl,
                         String fax, String email, String bizNo, String bizRegistrationUrl, String businessArea, String serviceName) {
        this.userEntity = userEntity;
        this.name = name;
        this.isMou = false;
        this.bizRegistrationUrl = bizRegistrationUrl;
        this.businessArea = businessArea;
        this.serviceName = serviceName;
        this.type = CompanyType.PARTICIPATING;
        this.address = new Address(mainAddress, mainZipCode, subAddress, subZipCode);
        this.representative = representative;
        this.foundedAt = foundedAt;
        this.take = take;
        this.workersCount = workersCount;
        this.manager = new Manager(managerName, managerPhoneNo, subManagerName, subManagerPhoneNo);
        this.email = email;
        this.fax = fax;
        this.bizNo = bizNo;
        this.companyIntroduce = companyIntroduce;
        this.companyLogoUrl = companyLogoUrl;
    }

    public void update(String mainAddress, String mainZipCode, String subAddress, String subZipCode,
                       double take, int workersCount, String managerName, String managerPhoneNo, String subManagerName,
                       String subManagerPhoneNo, String companyIntroduce, String companyLogoUrl, String fax, String email) {
        this.address.update(mainAddress, mainZipCode, subAddress, subZipCode);
        this.take = take;
        this.workersCount = workersCount;
        this.manager.update(managerName, managerPhoneNo, subManagerName, subManagerPhoneNo);
        this.companyIntroduce = companyIntroduce;
        this.companyLogoUrl = companyLogoUrl;
        this.fax = fax;
        this.email = email;
    }

    public CompanyEntity changeCompanyType(CompanyType type) {
        this.type = type;
        return this;
    }

    public CompanyEntity convertToMou() {
        this.isMou = !this.isMou;
        return this;
    }
}
