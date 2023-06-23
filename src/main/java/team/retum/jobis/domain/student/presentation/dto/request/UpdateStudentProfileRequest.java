package team.retum.jobis.domain.student.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdateStudentProfileRequest {

    @NotBlank
    private String profileImageUrl;

}
