package team.retum.jobis.domain.notice.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ViewerVO {

    private final Long studentId;
    private final String studentName;
    private final Integer grade;
    private final Integer classRoom;
    private final Integer number;
    private final LocalDateTime viewedAt;

    public String getStudentGcn() {
        return String.format("%d%d%02d", grade, classRoom, number);
    }
}
