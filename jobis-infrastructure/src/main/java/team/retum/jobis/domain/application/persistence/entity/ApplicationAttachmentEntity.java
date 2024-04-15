package team.retum.jobis.domain.application.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.application.model.AttachmentType;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_application_attachment")
@Entity
public class ApplicationAttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(400)")
    private String attachmentUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(4)")
    private AttachmentType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private ApplicationEntity application;

    public ApplicationAttachmentEntity(String attachmentUrl, AttachmentType type) {
        this.attachmentUrl = attachmentUrl;
        this.type = type;
    }

    protected void setApplication(ApplicationEntity application) {
        if (this.application != null) {
            return;
        }
        this.application = application;
    }
}
