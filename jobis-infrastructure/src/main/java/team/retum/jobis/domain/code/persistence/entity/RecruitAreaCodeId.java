package team.retum.jobis.domain.code.persistence.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class RecruitAreaCodeId implements Serializable {
    private Long recruitArea;
    private Long code;
}
