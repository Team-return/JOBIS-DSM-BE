package team.retum.jobis.domain.application.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import com.example.jobisapplication.domain.application.domain.ApplicationStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class TeacherQueryApplicationsResponse {

    private final List<TeacherQueryApplicationResponse> applications;
    private final int totalPageCount;

    @Getter
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
