package team.retum.jobis.domain.user.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;
import team.retum.jobis.domain.auth.model.Authority;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class User {

    private final Long id;

    private final String accountId;

    private final String password;

    private final Authority authority;

    private final String token;

    public User updatePassword(String password) {
        return this.toBuilder()
            .password(password)
            .build();
    }

    public User removeDeviceToken() {
        return this.toBuilder()
            .token(null)
            .build();
    }

    public User setToken(String token) {
        if (token != null) {
            return this.toBuilder()
                .token(token)
                .build();
        } else {
            return this;
        }
    }
}
