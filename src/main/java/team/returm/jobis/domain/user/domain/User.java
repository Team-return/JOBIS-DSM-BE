package team.returm.jobis.domain.user.domain;

import team.returm.jobis.global.entity.BaseEntity;
import team.returm.jobis.domain.user.domain.enums.Authority;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity {

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)", unique = true)
    private String accountId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(60)")
    private String password;

    @Column(columnDefinition = "VARCHAR(7)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public User(String accountId, String password, Authority authority) {
        this.accountId = accountId;
        this.password = password;
        this.authority = authority;
    }
}