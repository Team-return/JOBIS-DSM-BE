package team.retum.jobis.domain.auth.dto;

import lombok.Getter;

@Getter
public class VerifyAuthCodeRequest {

    private String email;

    private String authCode;
}
