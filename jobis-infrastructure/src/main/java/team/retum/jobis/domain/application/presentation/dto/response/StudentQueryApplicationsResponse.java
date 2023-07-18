package team.retum.jobis.domain.application.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import com.example.jobisapplication.domain.application.domain.ApplicationStatus;

import java.util.List;

@Getter
@AllArgsConstructor
public class StudentQueryApplicationsResponse {

    private final List<StudentQueryApplicationResponse> applications;

    @Getter
    @Builder
    public static class StudentQueryApplicationResponse {

        private final Long applicationId;
        private final String company;
        private final List<AttachmentResponse> attachments;
        private final ApplicationStatus applicationStatus;
    }
}
