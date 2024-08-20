package team.retum.jobis.domain.interest.spi;

import team.retum.jobis.domain.interest.model.Interest;

public interface CommandInterestPort {

    Interest saveInterest(Interest interest);

    void deleteInterest(Interest interest);
}
