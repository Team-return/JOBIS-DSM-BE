package team.retum.jobis.domain.student.persistence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
