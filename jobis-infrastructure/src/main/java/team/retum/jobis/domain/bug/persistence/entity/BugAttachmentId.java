package team.retum.jobis.domain.bug.persistence.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class BugAttachmentId implements Serializable {
    private Long bugReport;
    private String attachmentUrl;
}
