package team.retum.jobis.domain.interest.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.exception.CodeNotFoundException;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.code.persistence.repository.CodeJpaRepository;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.interest.dto.response.InterestResponse;
import team.retum.jobis.domain.interest.model.Interest;
import team.retum.jobis.domain.interest.persistence.mapper.InterestMapper;
import team.retum.jobis.domain.interest.persistence.repository.InterestJpaRepository;
import team.retum.jobis.domain.interest.spi.InterestPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class InterestPersistenceAdapter implements InterestPort {

    private final InterestJpaRepository interestJpaRepository;
    private final CodeJpaRepository codeJpaRepository;
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
    public List<Interest> getAllByStudentIdAndCodes(Long studentId, List<Long> codes) {
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
}
