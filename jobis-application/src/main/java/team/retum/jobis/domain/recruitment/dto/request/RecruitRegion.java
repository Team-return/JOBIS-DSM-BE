package team.retum.jobis.domain.recruitment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecruitRegion {
    SEOUL("서울"),
    DAEJEON("대전"),
    GYEONGGI("경기"),
    OTHERS(null),;

    private final String regionName;
}
