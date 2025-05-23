package team.retum.jobis.domain.interest.persistence;

import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.interest.dto.response.InterestResponse;
import team.retum.jobis.domain.interest.exception.InvalidCodesException;
import team.retum.jobis.domain.interest.model.Interest;
import team.retum.jobis.domain.interest.persistence.mapper.InterestMapper;
import team.retum.jobis.domain.interest.persistence.repository.InterestJpaRepository;
import team.retum.jobis.domain.interest.spi.InterestPort;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static team.retum.jobis.domain.interest.persistence.entity.QInterestEntity.interestEntity;

@RequiredArgsConstructor
@Component
public class InterestPersistenceAdapter implements InterestPort {

    private final JPAQueryFactory jpaQueryFactory;
    private final InterestJpaRepository interestJpaRepository;
    private final InterestMapper interestMapper;
    private final QueryCodePort queryCodePort;

    @Override
    public void save(Interest interest) {
        interestMapper.toDomain(
                interestJpaRepository.save(interestMapper.toEntity(interest))
        );
    }

    @Override
    public void delete(Interest interest) {
        interestJpaRepository.delete(interestMapper.toEntity(interest));
    }

    @Override
    public List<Interest> getAllByStudentIdAndCodes(Long studentId, List<Long> codeIds) {
        if (codeIds == null || codeIds.isEmpty()) {
            throw InvalidCodesException.EXCEPTION;
        }

        return interestJpaRepository.findByStudentId(studentId).stream()
            .map(interestMapper::toDomain)
            .toList();
    }

    @Override
    public List<Interest> getAllByStudentId(Long studentId) {
        return interestJpaRepository.findByStudentId(studentId).stream()
            .map(interestMapper::toDomain)
            .toList();
    }

    @Override
    public List<InterestResponse> getByStudentId(Long studentId) {
        List<Interest> interests = getAllByStudentId(studentId);

        return interests.stream()
                .map(interest -> {
                    Code code = queryCodePort.getByIdOrThrow(interest.getCode());
                    return InterestResponse.of(interest, code);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Map<CodeType, List<String>> getAllByStudentIdAndCodeTypes(Long studentId) {
        return jpaQueryFactory
            .select(interestEntity.code.type, interestEntity.code.keyword)
            .from(interestEntity)
            .where(
                interestEntity.student.id.eq(studentId),
                interestEntity.code.type.in(CodeType.JOB, CodeType.TECH)
            )
            .transform(GroupBy.groupBy(interestEntity.code.type)
                .as(GroupBy.list(interestEntity.code.keyword))
            );
    }
}
