package team.retum.jobis.domain.interview.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.interview.model.Interview;
import team.retum.jobis.domain.interview.persistence.mapper.InterviewMapper;
import team.retum.jobis.domain.interview.persistence.repository.InterviewJpaRepository;
import team.retum.jobis.domain.interview.spi.InterviewPort;

@Repository
@RequiredArgsConstructor
public class InterviewPersistenceAdapter implements InterviewPort {

    private final InterviewMapper interviewMapper;
    private final InterviewJpaRepository interviewJpaRepository;

    @Override
    public Interview save(Interview interview) {
        return interviewMapper.toDomain(
            interviewJpaRepository.save(
                interviewMapper.toEntity(interview)
            )
        );
    }
}
