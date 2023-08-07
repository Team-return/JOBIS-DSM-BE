package team.retum.jobis.domain.student.persistence.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import team.retum.jobis.domain.application.persistence.entity.ApplicationEntity;
import team.retum.jobis.domain.bookmark.persistence.entity.BookmarkEntity;
import team.retum.jobis.domain.student.model.Department;
import team.retum.jobis.domain.student.model.Gender;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
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
        name = "tbl_student",
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
    private final List<ApplicationEntity> applications = new ArrayList<>();

    @OneToMany(mappedBy = "student", orphanRemoval = true)
    private List<BookmarkEntity> bookmarks = new ArrayList<>();

    @Builder
    public StudentEntity(Long id, UserEntity userEntity, String name, Integer grade,
                         Integer classRoom, Integer number, Gender gender,
                         Department department, String profileImageUrl) {
        this.id = id;
        this.userEntity = userEntity;
        this.name = name;
        this.grade = grade;
        this.classRoom = classRoom;
        this.number = number;
        this.gender = gender;
        this.department = department;
        this.profileImageUrl = profileImageUrl;
    }
}
