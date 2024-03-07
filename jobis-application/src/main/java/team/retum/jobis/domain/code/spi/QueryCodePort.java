package team.retum.jobis.domain.code.spi;

import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.model.CodeType;

import java.util.List;
import java.util.Optional;

public interface QueryCodePort {

    List<Code> queryCodesByKeywordAndType(String keyword, CodeType codeType, Long parentCode);

    Optional<Code> queryCodeById(Long codeId);

    List<Code> queryCodesByIdIn(List<Long> codes);

    Optional<Code> queryCodeByKeywordAndType(String keyword, CodeType type);
}
