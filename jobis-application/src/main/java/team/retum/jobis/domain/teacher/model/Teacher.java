package team.retum.jobis.domain.teacher.model;

import team.retum.jobis.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class Teacher {

    private final Long id;
    
}
