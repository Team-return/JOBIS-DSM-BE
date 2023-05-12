package team.returm.jobis.domain.review.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import team.returm.jobis.domain.review.domain.Review;

import java.util.List;


public interface ReviewRepository extends MongoRepository<Review, String> {

    List<Review> findByCompanyId(Long companyId);
}
