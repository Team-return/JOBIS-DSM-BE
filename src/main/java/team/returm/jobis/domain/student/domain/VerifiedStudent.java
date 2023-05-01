package team.returm.jobis.domain.student.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
public class VerifiedStudent {

    @Id
    private String studentGcn;

    @NotNull
    @Column(columnDefinition = "VARCHAR(10)")
    private String studentName;

}
