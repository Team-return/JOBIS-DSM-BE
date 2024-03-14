package team.retum.jobis.domain.recruitment.persistence.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.application.exception.InvalidDateException;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class RecruitDate {

    @NotNull
    @Column(columnDefinition = "DATE")
    private LocalDate startDate;

    @NotNull
    @Column(columnDefinition = "DATE")
    private LocalDate finishDate;

    public RecruitDate(LocalDate startDate, LocalDate finishDate) {
        this.startDate = startDate;
        this.finishDate = finishDate;
    }
}
