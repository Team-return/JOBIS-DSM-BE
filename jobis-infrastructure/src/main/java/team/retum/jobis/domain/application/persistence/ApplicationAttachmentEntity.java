package team.retum.jobis.domain.application.persistence;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.jobisapplication.domain.application.domain.AttachmentType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ApplicationAttachmentEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(400)")
    private String attachmentUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(4)")
    private AttachmentType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private ApplicationEntity applicationEntity;

    @Builder
    public ApplicationAttachmentEntity(String attachmentUrl, AttachmentType type, ApplicationEntity applicationEntity) {
        this.attachmentUrl = attachmentUrl;
        this.type = type;
        this.applicationEntity = applicationEntity;
    }
}
