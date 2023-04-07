package team.returm.jobis.domain.student.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.student.domain.types.Gender;
import team.returm.jobis.domain.user.domain.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"grade", "classRoom", "number"}
                )
        }
)
@Entity
public class Student {

    @Id
    @Column(name = "student_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id", nullable = false)
    private User user;

    @NotNull
    @Column(length = 10)
    private String name;

    @NotNull
    @Column(columnDefinition = "TINYINT")
    private Integer grade;

    @NotNull
    @Column(columnDefinition = "TINYINT")
    private Integer classRoom;

    @NotNull
    @Column(columnDefinition = "TINYINT")
    private Integer number;

    @Column(columnDefinition = "VARCHAR(6)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Builder
    public Student(User user, String name, Integer grade,
                   Integer classRoom, Integer number, Gender gender) {
        this.user = user;
        this.name = name;
        this.grade = grade;
        this.classRoom = classRoom;
        this.number = number;
        this.gender = gender;
    }

    public static String processGcn(int grade, int classNumber, int number) {
        return String.valueOf(grade) +
                classNumber +
                (number < 10 ? "0" + number : number);
    }
}
