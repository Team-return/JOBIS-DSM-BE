package team.retum.jobis.domain.bug.persistence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(BugAttachmentId.class)
@Table(name = "tbl_bug_attachment")
@Entity
public class BugAttachmentEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bug_report_id", nullable = false)
    private BugReportEntity bugReportEntity;

    @Id
    @NotNull
    @Column(columnDefinition = "VARCHAR(300)")
    private String attachmentUrl;
}
