package team.returm.jobis.domain.review.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReviewRepository {

    private final JPAQueryFactory queryFactory;
    private final ReviewJpaRepository reviewJpaRepository;
}
