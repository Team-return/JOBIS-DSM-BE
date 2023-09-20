package team.retum.jobis.domain.bug.persistence.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class BugAttachmentEntity {

    @NotNull
    @Column(columnDefinition = "VARCHAR(300)")
    private String attachmentUrl;
}
