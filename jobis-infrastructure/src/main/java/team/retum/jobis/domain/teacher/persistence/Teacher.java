package team.retum.jobis.domain.teacher.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.persistence.domain.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher_id", nullable = false)
    private User user;
}
