package team.returm.jobis.domain.student.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.bookmark.domain.Bookmark;
import team.returm.jobis.domain.student.domain.enums.Department;
import team.returm.jobis.domain.student.domain.enums.Gender;
import team.returm.jobis.domain.student.exception.ClassRoomNotFoundException;
import team.returm.jobis.domain.user.domain.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    @NotNull
    @Column(columnDefinition = "VARCHAR(6)")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    private Department department;

    @OneToMany(mappedBy = "student", orphanRemoval = true)
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "student", orphanRemoval = true)
    private final List<Bookmark> bookmarks = new ArrayList<>();

    @Builder
    public Student(User user, String name, Integer grade,
                   Integer classRoom, Integer number, Gender gender, Department department) {
        this.user = user;
        this.name = name;
        this.grade = grade;
        this.classRoom = classRoom;
        this.number = number;
        this.gender = gender;
        this.department = department;
    }

    public static String processGcn(int grade, int classNumber, int number) {
        return String.valueOf(grade) +
                classNumber +
                (number < 10 ? "0" + number : number);
    }

    public static Department getDepartment(Integer grade, Integer classRoom) {
        if (grade == 1) {
            return Department.COMMON;
        }

        return switch (classRoom) {
            case 1, 2 -> Department.SOFTWARE_DEVELOP;
            case 3 -> Department.EMBEDDED_SOFTWARE;
            case 4 -> Department.INFORMATION_SECURITY;
            default -> throw ClassRoomNotFoundException.EXCEPTION;
        };
    }
}
