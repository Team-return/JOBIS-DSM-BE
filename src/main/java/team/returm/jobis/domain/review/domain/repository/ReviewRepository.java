package team.returm.jobis.domain.review.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import team.returm.jobis.domain.review.domain.Review;


public interface ReviewRepository extends MongoRepository<Review, String> {

    Review findByCompanyId(Long id);
}
