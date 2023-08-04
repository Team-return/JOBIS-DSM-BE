package team.retum.jobis.domain.auth.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;
import team.retum.jobis.domain.auth.exception.BadAuthCodeException;
import team.retum.jobis.domain.auth.exception.UnverifiedEmailException;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class AuthCode {

    private final String email;

    private final String code;

    private final boolean isVerified;

    private final Integer ttl;

    public AuthCode verify() {
        return this.toBuilder()
                .isVerified(true)
                .build();
    }

    public void checkIsVerified() {
        if (!this.isVerified) {
            throw UnverifiedEmailException.EXCEPTION;
        }
    }

    public void verifyCode(String code) {
        if (!this.code.equals(code)) {
            throw BadAuthCodeException.EXCEPTION;
        }
    }
}
