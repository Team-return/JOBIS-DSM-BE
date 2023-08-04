package team.retum.jobis.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.auth.model.AuthCodeType;

@Getter
@Builder
public class SendAuthCodeRequest {

    private String email;

    private AuthCodeType authCodeType;
}
