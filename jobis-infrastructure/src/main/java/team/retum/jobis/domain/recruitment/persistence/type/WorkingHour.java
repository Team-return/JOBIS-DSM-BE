package team.retum.jobis.domain.recruitment.persistence.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
