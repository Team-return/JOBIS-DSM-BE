package team.returm.jobis.domain.review.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.domain.repository.CodeRepository;
import team.returm.jobis.domain.code.facade.CodeFacade;
import team.returm.jobis.domain.review.domain.QnAElement;
import team.returm.jobis.domain.review.domain.Review;
import team.returm.jobis.domain.review.domain.repository.ReviewRepository;
import team.returm.jobis.domain.review.exception.ReviewNotFoundException;
import team.returm.jobis.domain.review.presentation.dto.QueryReviewDetailResponse;
import team.returm.jobis.domain.review.presentation.dto.QueryReviewDetailResponse.QnAResponse;
import team.returm.jobis.global.annotation.Service;

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
                .createdDate(review.getCreatedDate())
                .qnaResponses(
                        QnAResponse.of(review.getQnAElements(), codes)
                )
                .build();
    }
}
