package team.returm.jobis.domain.review.domain.repository;

import org.springframework.data.repository.CrudRepository;
import team.returm.jobis.domain.review.domain.Review;

public interface ReviewJpaRepository extends CrudRepository<Review, Long> {
}
