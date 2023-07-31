package team.retum.jobis.domain.review.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.review.dto.CreateReviewRequest;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.spi.CommandReviewPort;
import team.retum.jobis.domain.review.spi.QueryReviewPort;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.spi.QueryStudentPort;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.review.exception.ReviewAlreadyExistsException;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;

import java.time.Year;

@RequiredArgsConstructor
@UseCase
public class CreateReviewUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final QueryApplicationPort queryApplicationPort;
    private final QueryStudentPort queryStudentPort;
    private final CommandReviewPort commandReviewPort;
    private final QueryReviewPort queryReviewPort;
    private final SecurityPort securityPort;

    public void execute(CreateReviewRequest request) {
        Company company = queryCompanyPort.queryCompanyById(request.getCompanyId())
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        Application application = queryApplicationPort.queryApplicationById(request.getApplicationId());
        application.checkReviewAuthority();

        Long currentUserId = securityPort.getCurrentUserId();
        Student student = queryStudentPort.queryStudentById(currentUserId)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        if (queryReviewPort.existsByCompanyIdAndStudentName(request.getCompanyId(), student.getName())) {
            throw ReviewAlreadyExistsException.EXCEPTION;
        }

        commandReviewPort.saveReview(
                Review.builder()
                        .companyId(company.getId())
                        .qnAElements(request.getQnaElementEntities())
                        .studentName(student.getName())
                        .year(Year.now().getValue())
                        .build()
        );
    }
}
