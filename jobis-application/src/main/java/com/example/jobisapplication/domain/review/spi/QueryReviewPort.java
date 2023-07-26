package com.example.jobisapplication.domain.review.spi;

import com.example.jobisapplication.domain.review.model.Review;

import java.util.List;
import java.util.Optional;

public interface QueryReviewPort {
    boolean existsByCompanyIdAndStudentName(Long companyId, String studentName);

    Optional<Review> findReviewById(String reviewId);

    List<Review> queryAllReviewsByCompanyId(Long companyId);

    Long queryReviewCountByCompanyId(Long companyId);
}
