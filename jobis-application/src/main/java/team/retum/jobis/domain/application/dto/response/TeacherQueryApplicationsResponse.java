package team.retum.jobis.domain.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.vo.ApplicationVO;
import team.retum.jobis.domain.student.model.SchoolNumber;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class TeacherQueryApplicationsResponse {

    private final List<TeacherQueryApplicationResponse> applications;

    public static TeacherQueryApplicationsResponse of(List<ApplicationVO> applicationVOs) {
        return new TeacherQueryApplicationsResponse(
            applicationVOs.stream()
                .map(application -> TeacherQueryApplicationResponse.builder()
                    .applicationId(application.getId())
                    .studentName(application.getName())
                    .studentGcn(
                        SchoolNumber.processSchoolNumber(
                            SchoolNumber.builder()
                                .grade(application.getGrade())
                                .classRoom(application.getClassNumber())
                                .number(application.getNumber())
                                .build()
                        )
                    )
                    .companyName(application.getCompanyName())
                    .attachments(
                        application.getApplicationAttachmentEntities().stream()
                            .map(AttachmentResponse::of).toList()
                    )
                    .createdAt(application.getCreatedAt().toLocalDate())
                    .applicationStatus(application.getApplicationStatus())
                    .build()
                ).toList()
        );
    }

    @Getter
    @NoArgsConstructor(force = true)
    @AllArgsConstructor
    @Builder
    public static class TeacherQueryApplicationResponse {

        private final Long applicationId;
        private final String studentName;
        private final String studentGcn;
        private final String companyName;
        private final List<AttachmentResponse> attachments;
        private final LocalDate createdAt;
        private final ApplicationStatus applicationStatus;
    }
}
