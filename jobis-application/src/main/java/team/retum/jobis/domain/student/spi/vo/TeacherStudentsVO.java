package team.retum.jobis.domain.student.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeacherStudentsVO {

    private final Long id;

    private final String name;

    private final Integer grade;

    private final Integer classRoom;

    private final Integer number;
}
