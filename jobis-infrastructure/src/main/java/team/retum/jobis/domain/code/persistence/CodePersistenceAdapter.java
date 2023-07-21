package team.retum.jobis.domain.code.persistence;

import com.example.jobisapplication.domain.code.exception.CodeNotFoundException;
import com.example.jobisapplication.domain.code.model.Code;
import com.example.jobisapplication.domain.code.spi.CodePort;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.example.jobisapplication.domain.code.model.CodeType;
import team.retum.jobis.domain.code.persistence.mapper.CodeMapper;
import team.retum.jobis.domain.code.persistence.repository.CodeJpaRepository;

import java.util.List;

import static team.retum.jobis.domain.code.domain.QCode.code;

@RequiredArgsConstructor
@Repository
public class CodePersistenceAdapter implements CodePort {

    private final CodeJpaRepository codeJpaRepository;
    private final CodeMapper codeMapper;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Code> queryCodesByKeywordAndType(String keyword, CodeType codeType, Long parentCode) {
        return jpaQueryFactory
                .selectFrom(code)
                .where(
                        code.codeType.eq(codeType),
                        containsKeyword(keyword),
                        eqParentCode(parentCode)
                ).fetch().stream()
                .map(codeMapper::toDomain)
                .toList();
    }

    @Override
    public Code queryCodeById(Long codeId) {
        return codeMapper.toDomain(
                codeJpaRepository.findById(codeId)
                        .orElseThrow(() -> CodeNotFoundException.EXCEPTION)
        );
    }

    //==conditions==//

    private BooleanExpression containsKeyword(String keyword) {
        return keyword == null ? null : code.keyword.contains(keyword);
    }

    private BooleanExpression eqParentCode(Long parentCode) {
        return parentCode == null ? null : code.parentCode.id.eq(parentCode);
    }
}