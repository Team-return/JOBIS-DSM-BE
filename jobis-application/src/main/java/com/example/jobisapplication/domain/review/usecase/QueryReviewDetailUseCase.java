package com.example.jobisapplication.domain.review.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.code.model.Code;
import com.example.jobisapplication.domain.code.spi.QueryCodePort;
import com.example.jobisapplication.domain.review.model.QnAElement;
import com.example.jobisapplication.domain.review.model.Review;
import com.example.jobisapplication.domain.review.spi.QueryReviewPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.review.exception.ReviewNotFoundException;
import com.example.jobisapplication.domain.review.dto.QueryReviewDetailResponse;
import com.example.jobisapplication.domain.review.dto.QueryReviewDetailResponse.QnAResponse;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class QueryReviewDetailUseCase {

    private final QueryReviewPort queryReviewPort;
    private final QueryCodePort queryCodePort;

    public QueryReviewDetailResponse execute(String reviewId) {
        Review review = queryReviewPort.findReviewById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        List<Long> codeIds = review.getQnAElements().stream()
                .map(QnAElement::getCodeId)
                .toList();

        List<Code> codeEntities = queryCodePort.queryCodesByIdIn(codeIds);

        return QueryReviewDetailResponse.builder()
                .year(review.getYear())
                .writer(review.getStudentName())
                .qnaResponses(
                        QnAResponse.of(review.getQnAElements(), codeEntities)
                )
                .build();
    }
}
