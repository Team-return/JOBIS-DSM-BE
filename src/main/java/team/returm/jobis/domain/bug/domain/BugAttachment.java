package team.returm.jobis.domain.bug.domain;

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
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(BugAttachmentId.class)
@Entity
public class BugAttachment {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bug_report_id", nullable = false)
    private BugReport bugReport;

    @Id
    @NotNull
    @Column(columnDefinition = "VARCHAR(300)", nullable = false)
    private String attachmentUrl;
}
