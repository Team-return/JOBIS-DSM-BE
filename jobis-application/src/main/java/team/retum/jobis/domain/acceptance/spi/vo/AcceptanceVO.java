package team.retum.jobis.domain.acceptance.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AcceptanceVO {

    private final Long acceptanceId;
    private final Integer grade;
    private final Integer classRoom;
    private final Integer number;
    private final String studentName;
    private final LocalDate contractDate;
}
