package team.retum.jobis.domain.code.persistence.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitAreaEntity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_recruit_area_code")
@Entity
public class RecruitAreaCodeEntity {

    @EmbeddedId
    private RecruitAreaCodeId recruitAreaCodeId;

    @MapsId("recruitAreaId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_area_id", nullable = false)
    private RecruitAreaEntity recruitArea;


    @MapsId("code")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_id", nullable = false)
    private CodeEntity code;

    @Enumerated(EnumType.STRING)
    private CodeType type;
}