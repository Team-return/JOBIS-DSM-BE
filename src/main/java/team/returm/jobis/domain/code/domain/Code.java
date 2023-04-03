package team.returm.jobis.domain.code.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import team.returm.jobis.domain.code.domain.enums.JobType;

@Getter
@BatchSize(size = 100)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(8)")
    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    private String keyword;
}
