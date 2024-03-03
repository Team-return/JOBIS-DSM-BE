package team.retum.jobis.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import team.retum.jobis.domain.user.dto.SetDeviceTokenRequest;

public class SetDeviceTokenWebRequest {

    @NotNull
    private String token;

    public SetDeviceTokenRequest toDomainRequest() {
        return new SetDeviceTokenRequest(this.token);
    }
}
