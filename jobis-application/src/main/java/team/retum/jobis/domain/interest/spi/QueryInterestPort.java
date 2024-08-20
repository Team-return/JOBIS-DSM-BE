package team.retum.jobis.domain.interest.spi;

import team.retum.jobis.domain.interest.model.Interest;

import java.util.List;
import java.util.Optional;

public interface QueryInterestPort {

    Optional<Interest> findByStudentIdAndCode(Long studentId, Long code);

    List<Interest> findAllByStudentId(Long studentId);

}
