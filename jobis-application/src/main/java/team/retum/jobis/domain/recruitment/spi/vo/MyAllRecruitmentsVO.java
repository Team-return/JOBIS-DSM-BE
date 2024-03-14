package team.retum.jobis.domain.recruitment.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class MyAllRecruitmentsVO {
    private final Long id;
    private final RecruitmentAreaElement recruitmentAreas;
    private final LocalDateTime createdAt;
}
