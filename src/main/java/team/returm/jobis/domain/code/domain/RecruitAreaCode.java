package team.returm.jobis.domain.code.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.recruitment.domain.RecruitArea;


@Getter
@BatchSize(size = 200)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(RecruitAreaCodeId.class)
public class RecruitAreaCode {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_area_id", nullable = false)
    private RecruitArea recruitArea;

    @Id
    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String codeKeyword;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(4)")
    private CodeType codeType;
}