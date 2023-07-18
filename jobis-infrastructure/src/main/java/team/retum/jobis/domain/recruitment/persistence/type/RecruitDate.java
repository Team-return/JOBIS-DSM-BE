package team.retum.jobis.domain.recruitment.persistence.type;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.jobisapplication.domain.application.exception.InvalidDateException;

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
        if (start.isAfter(end)) {
            throw InvalidDateException.EXCEPTION;
        }
    }
}
