package team.returm.jobis.domain.code.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.recruitment.domain.RecruitArea;

import javax.persistence.*;


@Getter
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_id", nullable = false)
    private Code code;
}