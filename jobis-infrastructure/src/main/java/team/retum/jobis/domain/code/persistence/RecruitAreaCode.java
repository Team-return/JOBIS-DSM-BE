package team.retum.jobis.domain.code.persistence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.recruitment.persistence.RecruitArea;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


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