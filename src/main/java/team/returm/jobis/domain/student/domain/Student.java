package team.returm.jobis.domain.student.domain;

import team.returm.jobis.domain.student.domain.types.Gender;
import team.returm.jobis.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Student {

    @Id
    @Column(name = "student_id")
    private UUID id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", columnDefinition = "BINARY(16)", nullable = false)
    private User user;

    @NotNull
    @Column(length = 10)
    private String name;

    @NotNull
    @Column(columnDefinition = "VARCHAR(30)")
    private String email;

    @NotNull
    @Column(columnDefinition = "VARCHAR(12)")
    private String phoneNumber;

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
    public Student(User user, String name, String email, String phoneNumber,
                   Integer grade, Integer classRoom, Integer number, Gender gender) {
        this.user = user;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
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
