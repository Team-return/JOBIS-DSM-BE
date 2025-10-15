package team.retum.jobis.domain.student.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_verified_student")
@Entity
public class VerifiedStudentEntity {

    @Id
    @Column(columnDefinition = "CHAR(4)")
    private String gcn;

    @NotNull
    @Column(columnDefinition = "VARCHAR(10)")
    private String name;
}
