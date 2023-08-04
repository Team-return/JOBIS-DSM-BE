package team.retum.jobis.domain.teacher.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

@Getter
@Builder
@Aggregate
public class Teacher {

    private final Long id;

}
