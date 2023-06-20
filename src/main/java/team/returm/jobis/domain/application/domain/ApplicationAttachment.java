package team.returm.jobis.domain.application.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.application.domain.enums.AttachmentType;

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
public class ApplicationAttachment {

    @Id
    @Column(columnDefinition = "VARCHAR(400)")
    private String attachmentUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(4)")
    private AttachmentType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @Builder
    public ApplicationAttachment(String attachmentUrl, AttachmentType attachmentType, Application application) {
        this.attachmentUrl = attachmentUrl;
        this.attachmentType = attachmentType;
        this.application = application;
    }
}
