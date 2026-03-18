package team.retum.jobis.domain.bookmark.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StudentBookmarksVO {

    private final String companyName;
    private final String companyLogoUrl;
    private final Long recruitmentId;
    private final LocalDateTime createdAt;
    private final boolean isBookmarked;
    private final RecruitStatus status;
    private final String jobCodes;
    private final Boolean militarySupport;
}
