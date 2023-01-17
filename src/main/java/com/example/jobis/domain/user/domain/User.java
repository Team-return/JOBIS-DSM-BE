package com.example.jobis.domain.user.domain;

import com.example.jobis.global.entity.BaseTimeEntity;
import com.example.jobis.domain.user.domain.enums.Authority;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @NotNull
    @Column(columnDefinition = "VARCHAR(20)", unique = true)
    private String accountId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(60)")
    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public User(String accountId, String password, Authority authority) {
        this.accountId = accountId;
        this.password = password;
        this.authority = authority;
    }
}
