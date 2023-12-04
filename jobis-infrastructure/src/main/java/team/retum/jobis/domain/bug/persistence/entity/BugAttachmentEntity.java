package team.retum.jobis.domain.bug.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class BugAttachmentEntity {

    @NotNull
    @Column(columnDefinition = "VARCHAR(300)")
    private String attachmentUrl;
}
