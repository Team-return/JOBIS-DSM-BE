package team.retum.jobis.domain.student.persistence;

import team.retum.jobis.domain.student.spi.VerifiedStudentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.student.persistence.repository.VerifiedStudentJpaRepository;

@Repository
@RequiredArgsConstructor
public class VerifiedStudentPersistenceAdapter implements VerifiedStudentPort {

    private final VerifiedStudentJpaRepository verifiedStudentJpaRepository;

    @Override
    public void deleteVerifiedStudentByGcnAndName(String gcn, String name) {
        verifiedStudentJpaRepository.deleteByGcnAndName(gcn, name);
    }

    @Override
    public boolean existsVerifiedStudentByGcnAndName(String gcn, String name) {
        return verifiedStudentJpaRepository.existsByGcnAndName(gcn, name);
    }
}
