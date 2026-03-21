package team.retum.jobis.domain.bookmark.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.bookmark.spi.vo.StudentBookmarksVO;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryStudentBookmarksResponse {

    private final List<QueryStudentBookmarkResponse> bookmarks;

    @Getter
    @Builder
    public static class QueryStudentBookmarkResponse {
        private final String companyName;
        private final String companyLogoUrl;
        private final Long recruitmentId;
        private final LocalDateTime createdAt;
        private final boolean isBookmarked;
        private final RecruitStatus status;
        private final String hiringJob;
        private final Boolean militarySupport;

        public static QueryStudentBookmarkResponse from(StudentBookmarksVO vo) {
            return QueryStudentBookmarkResponse.builder()
                .companyName(vo.getCompanyName())
                .companyLogoUrl(vo.getCompanyLogoUrl())
                .recruitmentId(vo.getRecruitmentId())
                .createdAt(vo.getCreatedAt())
                .isBookmarked(vo.isBookmarked())
                .status(vo.getStatus())
                .hiringJob(vo.getJobCodes())
                .militarySupport(vo.getMilitarySupport())
                .build();
        }
    }
}
