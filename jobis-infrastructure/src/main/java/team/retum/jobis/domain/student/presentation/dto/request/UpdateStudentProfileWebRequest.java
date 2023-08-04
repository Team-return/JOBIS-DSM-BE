package team.retum.jobis.domain.student.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.student.dto.UpdateStudentProfileRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdateStudentProfileWebRequest {

    @Size(max = 300)
    @NotBlank
    private String profileImageUrl;

    public UpdateStudentProfileRequest toDomainRequest() {
        return UpdateStudentProfileRequest.builder()
                .profileImageUrl(this.profileImageUrl)
                .build();
    }

}
