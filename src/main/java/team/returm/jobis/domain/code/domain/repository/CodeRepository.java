package team.returm.jobis.domain.code.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.domain.enums.CodeType;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CodeRepository {

    private final CodeJpaRepository codeJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public List<Code> queryCodesByKeywordAndType(String keyword, CodeType codeType) {
        return codeJpaRepository.findCodeByKeywordContainingAndCodeType(keyword, codeType);
    }

    public List<Code> queryCodesByIdIn(List<Long> codeIds) {
        return codeJpaRepository.findCodesByIdIn(codeIds);
    }
}