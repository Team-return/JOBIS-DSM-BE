package team.retum.jobis.domain.code.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.code.exception.CodeNotFoundException;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.persistence.mapper.CodeMapper;
import team.retum.jobis.domain.code.persistence.repository.CodeJpaRepository;
import team.retum.jobis.domain.code.spi.CodePort;

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
    public List<Code> getAllByKeywordAndType(String keyword, CodeType codeType, Long parentCode) {
        return jpaQueryFactory
            .selectFrom(codeEntity)
            .where(
                codeEntity.type.eq(codeType),
                codeEntity.isPublic.eq(true),
                containsKeyword(keyword),
                eqParentCode(parentCode)
            ).fetch().stream()
            .map(codeMapper::toDomain)
            .toList();
    }

    @Override
    public Code getByIdOrThrow(Long codeId) {
        return codeMapper.toDomain(
            codeJpaRepository.findById(codeId)
                .orElseThrow(() -> CodeNotFoundException.EXCEPTION)
        );
    }

    @Override
    public List<Code> getAllByIdInOrThrow(List<Long> codes) {
        List<Code> result = codeJpaRepository.findCodesByCodeIn(codes).stream()
            .map(codeMapper::toDomain)
            .toList();
        if (result.size() != codes.size()) {
            throw CodeNotFoundException.EXCEPTION;
        }

        return result;
    }

    @Override
    public Optional<Code> getByKeywordAndType(String keyword, CodeType type) {
        return codeJpaRepository.findByKeywordAndType(keyword, type)
            .map(codeMapper::toDomain);
    }

    @Override
    public Code save(Code code) {
        return codeMapper.toDomain(
            codeJpaRepository.save(codeMapper.toEntity(code))
        );
    }

    @Override
    public void existsByAllCodeIds(List<Long> codeIds) {
        Long count = jpaQueryFactory
            .select(codeEntity.count())
            .from(codeEntity)
            .where(codeEntity.code.in(codeIds))
            .fetchOne();

        if (count == null || !count.equals((long) codeIds.size())) {
            throw CodeNotFoundException.EXCEPTION;
        }
    }

    @Override
    public boolean existsByCodeId(Long codeId) {
        return codeJpaRepository.existsById(codeId);
    }

    //==conditions==//

    private BooleanExpression containsKeyword(String keyword) {
        return keyword == null ? null : codeEntity.keyword.contains(keyword);
    }

    private BooleanExpression eqParentCode(Long parentCode) {
        return parentCode == null ? null : codeEntity.parentCode.code.eq(parentCode);
    }
}
