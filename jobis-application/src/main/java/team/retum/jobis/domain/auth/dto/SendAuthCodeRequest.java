package team.retum.jobis.domain.auth.dto;

import team.retum.jobis.domain.auth.model.AuthCodeType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SendAuthCodeRequest {

    private String email;

    private AuthCodeType authCodeType;
}
