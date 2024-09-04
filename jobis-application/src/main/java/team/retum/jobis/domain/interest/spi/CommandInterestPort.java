package team.retum.jobis.domain.interest.spi;

import team.retum.jobis.domain.interest.model.Interest;

public interface CommandInterestPort {

    void save(Interest interest);

    void delete(Interest interest);
}
