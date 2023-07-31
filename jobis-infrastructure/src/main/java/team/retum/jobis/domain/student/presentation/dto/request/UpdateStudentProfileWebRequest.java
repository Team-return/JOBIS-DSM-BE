package team.retum.jobis.domain.student.presentation.dto.request;

import team.retum.jobis.domain.student.dto.UpdateStudentProfileRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdateStudentProfileWebRequest {

    @NotBlank
    private String profileImageUrl;

    public UpdateStudentProfileRequest toDomainRequest() {
        return UpdateStudentProfileRequest.builder()
                .profileImageUrl(this.profileImageUrl)
                .build();
    }

}
