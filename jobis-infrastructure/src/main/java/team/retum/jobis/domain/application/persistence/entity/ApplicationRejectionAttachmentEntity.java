package team.retum.jobis.domain.application.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_application_rejection_attachment")
@Entity
public class ApplicationRejectionAttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(300)")
    private String attachmentUrl;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private ApplicationEntity application;

    public ApplicationRejectionAttachmentEntity(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    protected void setApplication(ApplicationEntity application) {
        if (this.application != null) {
            return;
        }
        this.application = application;
    }
}
