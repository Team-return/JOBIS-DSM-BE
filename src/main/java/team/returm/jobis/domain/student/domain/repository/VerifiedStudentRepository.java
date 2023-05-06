package team.returm.jobis.domain.student.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.returm.jobis.domain.student.domain.VerifiedStudent;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class VerifiedStudentRepository {

    private final JPAQueryFactory queryFactory;
    private final VerifiedStudentJpaRepository verifiedStudentJpaRepository;

    public Optional<VerifiedStudent> queryVerifiedStudentByGcnAndName(String gcn, String name) {
        return verifiedStudentJpaRepository.findByGcnAndName(gcn, name);
    }

    public void deleteVerifiedStudentByGcnAndName(String gcn, String name) {
        verifiedStudentJpaRepository.deleteByGcnAndName(gcn, name);
    }
}
