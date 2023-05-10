package team.returm.jobis.domain.review.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.exception.ApplicationNotFoundException;
import team.returm.jobis.domain.review.domain.Review;
import team.returm.jobis.domain.review.domain.repository.ReviewRepository;
import team.returm.jobis.domain.review.exception.ReviewCannotWriteException;
import team.returm.jobis.domain.review.presentation.dto.CreateReviewRequest;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class CreateReviewService {

    private final UserFacade userFacade;
    private final ApplicationRepository applicationRepository;
    private final ReviewRepository reviewRepository;

    public void execute(CreateReviewRequest request) {

        Long currentUserId = userFacade.getCurrentUserId();

        Application application = applicationRepository.queryApplicationByStudentId(currentUserId)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);

        if (application.getApplicationStatus().equals(ApplicationStatus.REQUESTED)) {
            throw ReviewCannotWriteException.EXCEPTION;
        }

        reviewRepository.save(Review.builder()
                .companyId(request.getCompanyId())
                .qnAElements(request.getQnaElements())
                .studentName(request.getStudentName())
                .year(request.getYear())
                .build());
    }
}
