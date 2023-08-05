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

    public User updatePassword(String password) {
        return this.toBuilder()
                .password(password)
                .build();
    }
}
