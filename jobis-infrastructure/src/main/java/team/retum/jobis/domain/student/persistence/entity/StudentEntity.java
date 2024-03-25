package team.retum.jobis.domain.student.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import team.retum.jobis.domain.student.model.Department;
import team.retum.jobis.domain.student.model.Gender;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import team.retum.jobis.global.util.ImageProperty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Table(
    name = "tbl_student",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"grade", "classRoom", "number", "entranceYear"})
    }
)
@Entity
public class StudentEntity {

    @Id
    @Column(name = "student_id")
    private Long id;

    @JsonIgnore
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
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

    @NotNull
    @Column(columnDefinition = "YEAR")
    private Integer entranceYear;

    @Builder
    public StudentEntity(Long id, UserEntity userEntity, String name, Integer grade,
                         Integer classRoom, Integer number, Gender gender,
                         Department department, String profileImageUrl, Integer entranceYear) {
        this.id = id;
        this.userEntity = userEntity;
        this.name = name;
        this.grade = grade;
        this.classRoom = classRoom;
        this.number = number;
        this.gender = gender;
        this.department = department;
        this.profileImageUrl = profileImageUrl;
        this.entranceYear = entranceYear;
    }
}
