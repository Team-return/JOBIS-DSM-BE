package team.retum.jobis.domain.auth.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

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
