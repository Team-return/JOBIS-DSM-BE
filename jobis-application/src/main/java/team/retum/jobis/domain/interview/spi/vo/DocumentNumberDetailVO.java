package team.retum.jobis.domain.interview.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DocumentNumberDetailVO {

    private final Long id;
    private final String documentNumber;
    private final List<InterviewVO> interviews;
}
