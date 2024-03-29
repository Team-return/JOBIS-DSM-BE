package team.retum.jobis.domain.student.persistence.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class VerifiedStudentRepository {

    private final JPAQueryFactory queryFactory;
    private final VerifiedStudentJpaRepository verifiedStudentJpaRepository;

    public boolean existsVerifiedStudentByGcnAndName(String gcn, String name) {
        return verifiedStudentJpaRepository.existsByGcnAndName(gcn, name);
    }

    public void deleteVerifiedStudentByGcnAndName(String gcn, String name) {
        verifiedStudentJpaRepository.deleteByGcnAndName(gcn, name);
    }
}
