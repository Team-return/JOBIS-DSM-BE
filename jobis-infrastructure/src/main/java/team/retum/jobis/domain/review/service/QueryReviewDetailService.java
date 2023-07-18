package team.retum.jobis.domain.review.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.code.domain.Code;
import team.retum.jobis.domain.code.facade.CodeFacade;
import team.retum.jobis.domain.review.domain.QnAElement;
import team.retum.jobis.domain.review.domain.Review;
import team.retum.jobis.domain.review.domain.repository.ReviewRepository;
import team.retum.jobis.domain.review.exception.ReviewNotFoundException;
import team.retum.jobis.domain.review.presentation.dto.QueryReviewDetailResponse;
import team.retum.jobis.domain.review.presentation.dto.QueryReviewDetailResponse.QnAResponse;
import team.retum.jobis.global.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryReviewDetailService {

    private final ReviewRepository reviewRepository;
    private final CodeFacade codeFacade;

    public QueryReviewDetailResponse execute(String reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        List<Long> codeIds = review.getQnAElements().stream()
                .map(QnAElement::getCodeId)
                .toList();

        List<Code> codes = codeFacade.queryCodesByIdIn(codeIds);

        return QueryReviewDetailResponse.builder()
                .year(review.getYear())
                .writer(review.getStudentName())
                .qnaResponses(
                        QnAResponse.of(review.getQnAElements(), codes)
                )
                .build();
    }
}
