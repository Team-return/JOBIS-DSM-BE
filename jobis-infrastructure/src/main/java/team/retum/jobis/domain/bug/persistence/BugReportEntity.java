package team.retum.jobis.domain.bug.persistence;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.jobisapplication.domain.bug.domain.DevelopmentArea;
import team.retum.jobis.global.entity.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_bug_report")
@Entity
public class BugReportEntity extends BaseTimeEntity {

    @OneToMany(mappedBy = "bugReport", orphanRemoval = true)
    private final List<BugAttachmentEntity> bugAttachmentEntities = new ArrayList<>();
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

    @Builder
    public BugReportEntity(String title, String content, DevelopmentArea developmentArea) {
        this.title = title;
        this.content = content;
        this.developmentArea = developmentArea;
    }
}
