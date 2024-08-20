package team.retum.jobis.domain.Interest.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.Interest.persistence.mapper.InterestMapper;
import team.retum.jobis.domain.Interest.persistence.repository.InterestJpaRepository;
import team.retum.jobis.domain.interest.model.Interest;
import team.retum.jobis.domain.interest.spi.InterestPort;

import java.util.List;
import java.util.Optional;

import static team.retum.jobis.domain.Interest.persistence.entity.QInterestEntity.interestEntity;

@RequiredArgsConstructor
@Component
public class InterestPersistenceAdapter implements InterestPort {

    private final InterestJpaRepository interestJpaRepository;
    private final InterestMapper interestMapper;
    private final JPAQueryFactory queryFactory;

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
}
