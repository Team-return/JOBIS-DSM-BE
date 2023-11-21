package team.retum.jobis.domain.code.persistence.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.model.JobType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
    @Column(columnDefinition = "VARCHAR(21)")
    private String keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_code_id")
    private CodeEntity parentCode;

    @Builder
    public CodeEntity(Long code, CodeType codeType, JobType jobType, String keyword, CodeEntity parentCodeEntity) {
        this.code = code;
        this.type = codeType;
        this.jobType = jobType;
        this.keyword = keyword;
        this.parentCode = parentCodeEntity;
    }
}
