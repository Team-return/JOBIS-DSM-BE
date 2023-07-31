package team.retum.jobis.domain.auth.model;

import team.retum.jobis.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class RefreshToken {

    private final Long id;

    private final String token;

    private final Authority authority;

    private final Long ttl;

    public RefreshToken update(String token, Long ttl) {
        return this.toBuilder()
                .token(token)
                .ttl(ttl)
                .build();
    }
}
