package team.retum.jobis.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class CompanySignInWebRequest {
    @Length(min = 10, max = 10)
    private String businessRegistrationNumber;

    @NotBlank
    private String authCode;
}
