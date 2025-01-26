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
import team.retum.jobis.domain.student.model.Student;

import java.util.List;
import java.util.Optional;

import static team.retum.jobis.domain.code.persistence.entity.QCodeEntity.codeEntity;
import static team.retum.jobis.domain.interest.persistence.entity.QInterestEntity.interestEntity;
import static team.retum.jobis.domain.student.persistence.entity.QStudentEntity.studentEntity;

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

    //interests 도메인에 있는게 맞는듯?
    @Override
    public List<String> getAllByStudentAndCodeType(Student student, CodeType type) {
        return jpaQueryFactory
            .select(interestEntity.code.keyword)
            .from(interestEntity)
            .innerJoin(studentEntity)
            .on(studentEntity.id.eq(student.getId()))
            .where(interestEntity.code.type.eq(type))
            .fetch().stream()
            .toList();
    }

    @Override
    public Code save(Code code) {
        return codeMapper.toDomain(
            codeJpaRepository.save(codeMapper.toEntity(code))
        );
    }

    //==conditions==//

    private BooleanExpression containsKeyword(String keyword) {
        return keyword == null ? null : codeEntity.keyword.contains(keyword);
    }

    private BooleanExpression eqParentCode(Long parentCode) {
        return parentCode == null ? null : codeEntity.parentCode.code.eq(parentCode);
    }
}
