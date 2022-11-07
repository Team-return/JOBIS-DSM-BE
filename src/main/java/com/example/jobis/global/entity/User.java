package com.example.jobis.global.entity;

import com.example.jobis.global.enums.Authority;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Size(min = 5, max = 20)
    private String accountId;

    @NotNull
    @Size(min=60)
    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    public User(String accountId, String password, Authority authority) {
        this.accountId = accountId;
        this.password = password;
        this.authority = authority;
    }
}
