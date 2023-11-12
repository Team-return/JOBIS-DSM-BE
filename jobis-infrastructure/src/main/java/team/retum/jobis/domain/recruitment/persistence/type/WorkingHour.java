package team.retum.jobis.domain.recruitment.persistence.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class WorkingHour {

    @NotNull
    @Column(columnDefinition = "TIME")
    private LocalTime startTime;

    @NotNull
    @Column(columnDefinition = "TIME")
    private LocalTime endTime;
}
