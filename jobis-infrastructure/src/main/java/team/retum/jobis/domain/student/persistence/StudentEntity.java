package team.retum.jobis.domain.student.persistence;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import team.retum.jobis.domain.application.persistence.ApplicationEntity;
import team.retum.jobis.domain.application.exception.InvalidGradeException;
import team.retum.jobis.domain.bookmark.persistence.BookmarkEntity;
import com.example.jobisapplication.domain.student.domain.Department;
import com.example.jobisapplication.domain.student.domain.Gender;
import team.retum.jobis.domain.student.exception.ClassRoomNotFoundException;
import team.retum.jobis.domain.user.persistence.UserEntity;
import team.retum.jobis.global.util.ImageProperty;

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
@DynamicInsert
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"grade", "classRoom", "number"}
                )
        }
)
@Entity
public class StudentEntity {

    @Id
    @Column(name = "student_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id", nullable = false)
    private UserEntity userEntity;

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

    @ColumnDefault(ImageProperty.DEFAULT_STUDENT_PROFILE_IMAGE)
    @Column(columnDefinition = "VARCHAR(300)", nullable = false)
    private String profileImageUrl;

    @OneToMany(mappedBy = "student", orphanRemoval = true)
    private List<ApplicationEntity> applicationEntities = new ArrayList<>();

    @OneToMany(mappedBy = "student", orphanRemoval = true)
    private final List<BookmarkEntity> bookmarkEntities = new ArrayList<>();

    @Builder
    public StudentEntity(UserEntity userEntity, String name, Integer grade,
                         Integer classRoom, Integer number, Gender gender, Department department, String profileImageUrl) {
        this.userEntity = userEntity;
        this.name = name;
        this.grade = grade;
        this.classRoom = classRoom;
        this.number = number;
        this.gender = gender;
        this.department = department;
        this.profileImageUrl = profileImageUrl;
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

    public void checkIs3rdGrade() {
        if (!this.grade.equals(3)) {
            throw InvalidGradeException.EXCEPTION;
        }
    }

    public void changeStudentProfile(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
