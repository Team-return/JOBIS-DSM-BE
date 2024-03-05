package team.retum.jobis.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SetDeviceTokenWebRequest {

    @NotNull
    private String token;
}
