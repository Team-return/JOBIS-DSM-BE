package team.retum.jobis.domain.code.persistence;

import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.spi.CodePort;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.code.persistence.mapper.CodeMapper;
import team.retum.jobis.domain.code.persistence.repository.CodeJpaRepository;

import java.util.List;
import java.util.Optional;

import static team.retum.jobis.domain.code.persistence.entity.QCodeEntity.codeEntity;

@RequiredArgsConstructor
@Repository
public class CodePersistenceAdapter implements CodePort {

    private final CodeJpaRepository codeJpaRepository;
    private final CodeMapper codeMapper;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Code> queryCodesByKeywordAndType(String keyword, CodeType codeType, Long parentCode) {
        return jpaQueryFactory
                .selectFrom(codeEntity)
                .where(
                        codeEntity.codeType.eq(codeType),
                        containsKeyword(keyword),
                        eqParentCode(parentCode)
                ).fetch().stream()
                .map(codeMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Code> queryCodeById(Long codeId) {
        return codeJpaRepository.findById(codeId)
                .map(codeMapper::toDomain);
    }

    @Override
    public List<Code> queryCodesByIdIn(List<Long> codes) {
        return codeJpaRepository.findCodesByIdIn(codes).stream()
                .map(codeMapper::toDomain)
                .toList();
    }

    //==conditions==//

    private BooleanExpression containsKeyword(String keyword) {
        return keyword == null ? null : codeEntity.keyword.contains(keyword);
    }

    private BooleanExpression eqParentCode(Long parentCode) {
        return parentCode == null ? null : codeEntity.parentCode.id.eq(parentCode);
    }
}