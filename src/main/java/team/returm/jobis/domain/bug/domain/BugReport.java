package team.returm.jobis.domain.bug.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.bug.domain.enums.DevelopmentArea;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BugReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    private String title;

    @NotNull
    @Column(columnDefinition = "VARCHAR(400)")
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(7)")
    private DevelopmentArea developmentArea;

    @OneToMany(mappedBy = "bugReport", orphanRemoval = true)
    private final List<BugAttachment> bugAttachments  = new ArrayList<>();

    @Builder
    public BugReport(String title, String content, DevelopmentArea developmentArea) {
        this.title = title;
        this.content = content;
        this.developmentArea = developmentArea;
    }
}
