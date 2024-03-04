package team.retum.jobis.domain.student.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateStudentProfileWebRequest {

    @Size(max = 300)
    @NotBlank
    private String profileImageUrl;

}
