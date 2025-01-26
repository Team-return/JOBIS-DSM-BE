package team.retum.jobis.domain.code.spi;

import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.interest.model.Interest;
import team.retum.jobis.domain.student.model.Student;

import java.util.List;
import java.util.Optional;

public interface QueryCodePort {

    List<Code> getAllByKeywordAndType(String keyword, CodeType codeType, Long parentCode);

    Code getByIdOrThrow(Long codeId);

    List<Code> getAllByIdInOrThrow(List<Long> codes);

    Optional<Code> getByKeywordAndType(String keyword, CodeType type);

    List<String> getAllByStudentAndCodeType(Student student, CodeType codeType);
}
