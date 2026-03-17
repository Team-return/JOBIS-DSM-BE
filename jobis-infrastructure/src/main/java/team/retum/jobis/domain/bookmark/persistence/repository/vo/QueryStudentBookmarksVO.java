package team.retum.jobis.domain.bookmark.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.bookmark.spi.vo.StudentBookmarksVO;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;

import java.time.LocalDateTime;

@Getter
public class QueryStudentBookmarksVO extends StudentBookmarksVO {

    @QueryProjection
    public QueryStudentBookmarksVO(
        String companyName,
        String companyLogoUrl,
        Long recruitmentId,
        LocalDateTime createdAt,
        Boolean militarySupport,
        RecruitStatus status,
        boolean isBookmarked,
        String jobCode
    ) {
        super(
            companyName,
            companyLogoUrl,
            recruitmentId,
            createdAt,
            isBookmarked,
            status,
            jobCode,
            militarySupport
        );
    }
}
