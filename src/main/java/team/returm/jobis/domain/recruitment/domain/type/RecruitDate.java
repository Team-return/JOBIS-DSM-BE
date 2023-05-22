package team.returm.jobis.domain.recruitment.domain.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.application.exception.InvalidDateException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
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
        validateDateRage(startDate, finishDate);
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    private void validateDateRage(LocalDate start, LocalDate end) {
        if(start.isBefore(end)) {
            throw InvalidDateException.EXCEPTION;
        }
    }
}
