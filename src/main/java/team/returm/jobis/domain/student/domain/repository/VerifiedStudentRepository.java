package team.returm.jobis.domain.student.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class VerifiedStudentRepository {

    private final JPAQueryFactory queryFactory;
    private final VerifiedStudentJpaRepository verifiedStudentJpaRepository;
    
}
