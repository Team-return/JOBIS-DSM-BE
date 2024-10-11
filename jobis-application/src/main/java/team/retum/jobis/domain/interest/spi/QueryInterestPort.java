package team.retum.jobis.domain.interest.spi;

import team.retum.jobis.domain.interest.dto.response.InterestResponse;
import team.retum.jobis.domain.interest.model.Interest;

import java.util.List;
import java.util.Optional;

public interface QueryInterestPort {

    List<Interest> getAllByStudentIdAndCodes(Long studentId, List<Long> codes);

    List<Interest> getAllByStudentId(Long studentId);

    List<InterestResponse> getByStudentId(Long studentId);
}
