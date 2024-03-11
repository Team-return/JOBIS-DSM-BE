package team.retum.jobis.domain.code.persistence.entity;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.model.JobType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_code")
@Entity
public class CodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(13)")
    private CodeType type;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(8)")
    private JobType jobType;

    @NotNull
    @Column(columnDefinition = "VARCHAR(30)")
    private String keyword;

    @NotNull
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isPublic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_code_id")
    private CodeEntity parentCode;

    @Builder
    public CodeEntity(Long code, CodeType codeType, JobType jobType, String keyword, boolean isPublic, CodeEntity parentCodeEntity) {
        this.code = code;
        this.type = codeType;
        this.jobType = jobType;
        this.keyword = keyword;
        this.isPublic = isPublic;
        this.parentCode = parentCodeEntity;
    }
}
