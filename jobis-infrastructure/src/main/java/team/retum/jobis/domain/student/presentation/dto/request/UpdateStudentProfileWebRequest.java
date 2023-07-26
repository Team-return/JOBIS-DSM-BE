package team.retum.jobis.domain.student.presentation.dto.request;

import com.example.jobisapplication.domain.student.dto.UpdateForgottenPasswordRequest;
import com.example.jobisapplication.domain.student.dto.UpdateStudentProfileRequest;
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
