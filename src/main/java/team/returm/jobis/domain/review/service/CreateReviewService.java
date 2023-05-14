package team.returm.jobis.domain.review.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.exception.ApplicationNotFoundException;
import team.returm.jobis.domain.review.domain.Review;
import team.returm.jobis.domain.review.domain.repository.ReviewRepository;
import team.returm.jobis.domain.review.presentation.dto.CreateReviewRequest;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.student.domain.repository.StudentRepository;
import team.returm.jobis.domain.student.exception.StudentNotFoundException;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

import java.time.Year;

@RequiredArgsConstructor
@Service
public class CreateReviewService {

    private final UserFacade userFacade;
    private final StudentRepository studentRepository;
    private final ApplicationRepository applicationRepository;
    private final ReviewRepository reviewRepository;

    public void execute(CreateReviewRequest request) {

        Long currentUserId = userFacade.getCurrentUserId();

        Student student =  studentRepository.queryStudentById(currentUserId)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        if (applicationRepository.existsApplicationByApplicationIdAndApplicationStatus(
                request.getApplicationId(), ApplicationStatus.REQUESTED)) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        reviewRepository.save(
                Review.builder()
                        .companyId(request.getCompanyId())
                        .qnAElements(request.getQnaElements())
                        .studentName(student.getName())
                        .year(Year.now().getValue())
                        .build()
        );
    }
}
