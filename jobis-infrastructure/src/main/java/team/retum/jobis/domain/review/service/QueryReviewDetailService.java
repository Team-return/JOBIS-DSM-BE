package team.retum.jobis.domain.review.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.code.persistence.CodeEntity;
import team.retum.jobis.domain.code.facade.CodeFacade;
import team.retum.jobis.domain.review.persistence.QnAElementEntity;
import team.retum.jobis.domain.review.persistence.ReviewEntity;
import team.retum.jobis.domain.review.persistence.repository.ReviewRepository;
import team.retum.jobis.domain.review.exception.ReviewNotFoundException;
import team.retum.jobis.domain.review.presentation.dto.QueryReviewDetailResponse;
import team.retum.jobis.domain.review.presentation.dto.QueryReviewDetailResponse.QnAResponse;
import com.example.jobisapplication.common.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryReviewDetailService {

    private final ReviewRepository reviewRepository;
    private final CodeFacade codeFacade;

    public QueryReviewDetailResponse execute(String reviewId) {

        ReviewEntity reviewEntity = reviewRepository.findById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        List<Long> codeIds = reviewEntity.getQnAElementEntities().stream()
                .map(QnAElementEntity::getCodeId)
                .toList();

        List<CodeEntity> codeEntities = codeFacade.queryCodesByIdIn(codeIds);

        return QueryReviewDetailResponse.builder()
                .year(reviewEntity.getYear())
                .writer(reviewEntity.getStudentName())
                .qnaResponses(
                        QnAResponse.of(reviewEntity.getQnAElementEntities(), codeEntities)
                )
                .build();
    }
}
