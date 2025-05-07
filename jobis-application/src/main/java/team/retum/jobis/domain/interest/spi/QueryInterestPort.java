package team.retum.jobis.domain.interest.spi;

import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.interest.dto.response.InterestResponse;
import team.retum.jobis.domain.interest.model.Interest;

import java.util.List;
import java.util.Map;

public interface QueryInterestPort {

    List<Interest> getAllByStudentIdAndCodes(Long studentId, List<Long> codeIds);

    List<Interest> getAllByStudentId(Long studentId);

    List<InterestResponse> getByStudentId(Long studentId);

    Map<CodeType, List<String>> getAllByStudentIdAndCodeTypes(Long studentId);
}
