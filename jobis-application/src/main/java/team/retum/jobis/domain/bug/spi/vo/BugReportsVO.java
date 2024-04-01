package team.retum.jobis.domain.bug.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.bug.model.DevelopmentArea;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BugReportsVO {

    private final Long id;
    private final String title;
    private final DevelopmentArea developmentArea;
    private final LocalDateTime createdAt;
}
