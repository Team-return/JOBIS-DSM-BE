package team.retum.jobis.domain.code.persistence.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitAreaEntity;


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
