package team.retum.jobis.domain.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.application.spi.vo.ApplicationVO;
import team.retum.jobis.domain.student.model.SchoolNumber;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class CompanyQueryApplicationsResponse {

    private final List<CompanyQueryApplicationResponse> applications;

    public static CompanyQueryApplicationsResponse of(List<ApplicationVO> applicationVOs) {
        return new CompanyQueryApplicationsResponse(
            applicationVOs.stream()
                .map(application -> CompanyQueryApplicationResponse.builder()
                    .applicationId(application.getId())
                    .studentName(application.getName())
                    .studentNumber(
                        SchoolNumber.processSchoolNumber(
                            SchoolNumber.builder()
                                .grade(application.getGrade())
                                .classRoom(application.getClassNumber())
                                .number(application.getNumber())
                                .build()
                        )
                    )
                    .profileImageUrl(application.getProfileImageUrl())
                    .attachments(
                        application.getApplicationAttachmentEntities().stream()
                            .map(AttachmentResponse::of).toList()
                    )
                    .createdAt(application.getCreatedAt().toLocalDate())
                    .build()
                ).toList()
        );
    }

    @Getter
    @Builder
    public static class CompanyQueryApplicationResponse {

        private final Long applicationId;
        private final String studentNumber;
        private final String studentName;
        private final String profileImageUrl;
        private final List<AttachmentResponse> attachments;
        private final LocalDate createdAt;
    }
}
