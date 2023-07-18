package team.retum.jobis.domain.company.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_company_attachment")
@Entity
public class CompanyAttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_attachment_id")
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(300)")
    private String attachmentUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity companyEntity;

    public CompanyAttachmentEntity(String attachmentUrl, CompanyEntity companyEntity) {
        this.attachmentUrl = attachmentUrl;
        this.companyEntity = companyEntity;
    }
}
