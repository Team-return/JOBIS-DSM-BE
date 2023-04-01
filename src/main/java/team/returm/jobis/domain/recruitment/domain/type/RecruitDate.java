package team.returm.jobis.domain.recruitment.domain.type;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class RecruitDate {

    @NotNull
    @Column(columnDefinition = "DATE")
    private LocalDate startDate;

    @NotNull
    @Column(columnDefinition = "DATE")
    private LocalDate finishDate;
}
