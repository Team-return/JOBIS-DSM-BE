package team.retum.jobis.domain.user.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.global.entity.BaseTimeEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_user")
@Entity
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(30)", unique = true)
    private String accountId;

    @NotNull
    @Column(columnDefinition = "CHAR(60)")
    private String password;

    @Column(columnDefinition = "CHAR(256)")
    private String deviceToken;

    @Column(columnDefinition = "VARCHAR(9)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public UserEntity(Long id, String accountId, String password, String deviceToken, Authority authority) {
        this.id = id;
        this.accountId = accountId;
        this.password = password;
        this.deviceToken = deviceToken;
        this.authority = authority;
    }

}
