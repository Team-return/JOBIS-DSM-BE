package team.retum.jobis.domain.recruitment.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class MyAllRecruitmentsVO {
    private final Long id;
    private final RecruitmentAreaElement recruitmentAreas;
    private final LocalDateTime createdAt;
}
