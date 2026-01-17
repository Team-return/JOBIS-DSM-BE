package team.retum.jobis.domain.interview.spi;

import team.retum.jobis.domain.interview.model.Interview;

public interface CommandInterviewPort {

    Interview save(Interview interview);
}
