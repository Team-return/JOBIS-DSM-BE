package team.retum.jobis.domain.company.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.company.persistence.entity.type.Address;
import team.retum.jobis.domain.company.persistence.entity.type.Manager;
import team.retum.jobis.global.converter.StringListConverter;
import team.retum.jobis.global.util.ImageProperty;

import java.time.LocalDate;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean isMou;

    @Embedded
    private Address address;

    @NotNull
    @Column(columnDefinition = "VARCHAR(10)")
    private String representative;

    @Column(columnDefinition = "VARCHAR(12)")
    private String representativePhoneNo;

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

    @NotNull
    @Column(columnDefinition = "VARCHAR(30)")
    private String email;

    @NotNull
    @Column(columnDefinition = "VARCHAR(1000)")
    private String companyIntroduce;

    @ColumnDefault(ImageProperty.DEFAULT_COMPANY_LOGO_IMAGE)
    @Column(columnDefinition = "VARCHAR(300)")
    private String companyLogoUrl;

    @Column(columnDefinition = "VARCHAR(300)")
    private String bizRegistrationUrl;

    @NotNull
    @Column(columnDefinition = "VARCHAR(30)")
    private String businessArea;

    @NotNull
    @Column(columnDefinition = "VARCHAR(40)")
    private String serviceName;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "VARCHAR(1000)")
    private List<String> attachmentUrls;

    @NotNull
    @Column(columnDefinition = "TINYINT(1)")
    private boolean headquarter;

    @Builder
    public CompanyEntity(Long id, String name, String mainAddress, String mainAddressDetail, String mainZipCode,
                         List<String> attachmentUrls, String representative, String representativePhoneNo, LocalDate foundedAt, double take,
                         int workersCount, String managerName, String managerPhoneNo, String companyIntroduce, String companyLogoUrl, String email,
                         String bizNo, String bizRegistrationUrl, String businessArea, String serviceName, CompanyType type,
                         boolean isMou, boolean headquarter) {
        this.id = id;
        this.name = name;
        this.isMou = isMou;
        this.bizRegistrationUrl = bizRegistrationUrl;
        this.businessArea = businessArea;
        this.serviceName = serviceName;
        this.type = type;
        this.address = new Address(mainAddress, mainAddressDetail, mainZipCode);
        this.representative = representative;
        this.representativePhoneNo = representativePhoneNo;
        this.foundedAt = foundedAt;
        this.take = take;
        this.workersCount = workersCount;
        this.manager = new Manager(managerName, managerPhoneNo);
        this.email = email;
        this.bizNo = bizNo;
        this.companyIntroduce = companyIntroduce;
        this.companyLogoUrl = companyLogoUrl;
        this.attachmentUrls = attachmentUrls;
        this.headquarter = headquarter;
    }
}
