package team.retum.jobis.domain.user.persistence;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.jobisapplication.domain.auth.domain.Authority;
import team.retum.jobis.global.entity.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(30)", unique = true)
    private String accountId;

    @NotNull
    @Column(columnDefinition = "CHAR(60)")
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

    public void updatePassword(String password) {
        this.password = password;
    }
}
