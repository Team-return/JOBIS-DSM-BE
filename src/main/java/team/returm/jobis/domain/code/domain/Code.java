package team.returm.jobis.domain.code.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.code.domain.enums.JobType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(13)")
    private CodeType codeType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(8)")
    private JobType jobType;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    private String keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_code_id")
    private Code parentCode;
}
