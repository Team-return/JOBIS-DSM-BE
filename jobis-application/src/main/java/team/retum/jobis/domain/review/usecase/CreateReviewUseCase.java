package team.retum.jobis.domain.review.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.review.dto.CreateReviewRequest;
import team.retum.jobis.domain.review.exception.ReviewAlreadyExistsException;
import team.retum.jobis.domain.review.model.QnA;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.spi.CommandReviewPort;
import team.retum.jobis.domain.review.spi.QueryReviewPort;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.spi.QueryStudentPort;

import java.util.List;

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

        Student student = queryStudentPort.queryStudentById(securityPort.getCurrentUserId())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        if (queryReviewPort.existsByCompanyIdAndStudentName(company.getId(), student.getName())) {
            throw ReviewAlreadyExistsException.EXCEPTION;
        }

        queryApplicationPort.queryApplicationById(request.getApplicationId())
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION)
                .checkReviewAuthority();

        Review review = commandReviewPort.saveReview(
                Review.builder()
                        .companyId(company.getId())
                        .studentId(student.getId())
                        .build()
        );

        List<QnA> qnAS = request.getQnAs().stream()
                .map(qnARequest -> QnA.builder()
                        .question(qnARequest.getQuestion())
                        .answer(qnARequest.getAnswer())
                        .reviewId(review.getId())
                        .codeId(qnARequest.getCodeId())
                        .build())
                .toList();
        commandReviewPort.saveAllQnAs(qnAS);
    }
}
