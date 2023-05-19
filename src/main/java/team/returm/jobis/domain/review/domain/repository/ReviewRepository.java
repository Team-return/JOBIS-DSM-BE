package team.returm.jobis.domain.review.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import team.returm.jobis.domain.review.domain.Review;

import java.util.List;


public interface ReviewRepository extends MongoRepository<Review, String> {

    List<Review> findAllByCompanyId(Long companyId);

    long countByCompanyId(Long companyId);

    boolean existsByCompanyIdAndStudentName(Long companyId, String studentName);
}
