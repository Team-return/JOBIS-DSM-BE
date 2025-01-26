package team.retum.jobis.domain.code.spi;

import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.model.CodeType;
import java.util.List;
import java.util.Optional;

public interface QueryCodePort {

    List<Code> getAllByKeywordAndType(String keyword, CodeType codeType, Long parentCode);

    Code getByIdOrThrow(Long codeId);

    List<Code> getAllByIdInOrThrow(List<Long> codes);

    Optional<Code> getByKeywordAndType(String keyword, CodeType type);
}
