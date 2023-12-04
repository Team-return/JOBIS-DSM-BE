package team.retum.jobis.domain.student.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.student.dto.UpdateStudentProfileRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
