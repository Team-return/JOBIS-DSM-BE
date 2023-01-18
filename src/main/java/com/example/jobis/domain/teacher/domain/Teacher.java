package com.example.jobis.domain.teacher.domain;

import com.example.jobis.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private User user;
}
