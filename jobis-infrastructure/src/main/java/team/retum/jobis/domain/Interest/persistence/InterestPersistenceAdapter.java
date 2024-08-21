package team.retum.jobis.domain.interest.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.interest.persistence.mapper.InterestMapper;
import team.retum.jobis.domain.interest.persistence.repository.InterestJpaRepository;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.interest.dto.response.InterestResponse;
import team.retum.jobis.domain.interest.model.Interest;
import team.retum.jobis.domain.interest.spi.InterestPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static team.retum.jobis.domain.interest.persistence.entity.QInterestEntity.interestEntity;

@RequiredArgsConstructor
@Component
public class InterestPersistenceAdapter implements InterestPort {

    private final InterestJpaRepository interestJpaRepository;
    private final InterestMapper interestMapper;
    private final JPAQueryFactory queryFactory;
    private final QueryCodePort queryCodePort;

    @Override
    public Interest saveInterest(Interest interest) {
        return interestMapper.toDomain(
                interestJpaRepository.save(interestMapper.toEntity(interest))
        );
    }

    @Override
    public void deleteInterest(Interest interest) {
        interestJpaRepository.delete(interestMapper.toEntity(interest));
    }

    @Override
    public Optional<Interest> findByStudentIdAndCode(Long studentId, Long code) {
        return interestJpaRepository.findByStudentIdAndCode(studentId, code)
                .map(interestMapper::toDomain);
    }

    @Override
    public List<Interest> findAllByStudentId(Long studentId) {
        return queryFactory
            .selectFrom(interestEntity)
            .where(interestEntity.studentId.eq(studentId))
            .fetch()
            .stream()
            .map(interestMapper::toDomain)
            .toList();
    }

    @Override
    public List<InterestResponse> findResponsesByStudentId(Long studentId) {
        List<Interest> interests = findAllByStudentId(studentId);

        return interests.stream()
                .map(interest -> {
                    Code code = queryCodePort.getByIdOrThrow(interest.getCode());
                    return InterestResponse.from(interest, code);
                })
                .collect(Collectors.toList());
    }
}
