package team.returm.jobis.domain.student.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"studentGcn", "studentName"}
                )
        }
)
@Entity
public class VerifiedStudent {

    @Id
    @Column(columnDefinition = "CHAR(4)")
    private String studentGcn;

    @NotNull
    @Column(columnDefinition = "VARCHAR(10)")
    private String studentName;

}
