package team.retum.jobis.domain.student.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateStudentProfileRequest {

    private String profileImageUrl;
}
