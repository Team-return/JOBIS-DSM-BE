package team.retum.jobis.domain.user.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.common.annotation.Aggregate;
import team.retum.jobis.domain.auth.model.Authority;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
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

    public User setToken(String token) {
        if (token != null) {
            return this.toBuilder()
                .token(token)
                .build();
        } else {
            return this;
        }
    }

    @Builder(toBuilder = true)
    public User(Long id, String accountId, String password, Authority authority, String token) {
        this.id = id;
        this.accountId = accountId;
        this.password = password;
        this.authority = authority;
        this.token = token;
    }
}
