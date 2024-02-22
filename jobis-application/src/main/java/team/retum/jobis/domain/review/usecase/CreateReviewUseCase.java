package team.retum.jobis.domain.review.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.review.dto.QnAElement;
import team.retum.jobis.domain.review.exception.ReviewAlreadyExistsException;
import team.retum.jobis.domain.review.model.QnA;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.spi.CommandReviewPort;
import team.retum.jobis.domain.review.spi.QueryReviewPort;
import team.retum.jobis.domain.student.model.Student;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CreateReviewUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final QueryApplicationPort queryApplicationPort;
    private final CommandReviewPort commandReviewPort;
    private final QueryReviewPort queryReviewPort;
    private final SecurityPort securityPort;

    public void execute(Long companyId, List<QnAElement> qnAElements) {
        Company company = queryCompanyPort.queryCompanyById(companyId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        Student student = securityPort.getCurrentStudent();

        if (queryReviewPort.existsByCompanyIdAndStudentId(company.getId(), student.getId())) {
            throw ReviewAlreadyExistsException.EXCEPTION;
        }

        queryApplicationPort.queryApplicationByCompanyIdAndStudentId(company.getId(), student.getId())
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION)
                .checkReviewAuthority();

        Review review = commandReviewPort.saveReview(
                Review.builder()
                        .companyId(company.getId())
                        .studentId(student.getId())
                        .build()
        );

        List<QnA> qnAs = qnAElements.stream()
                .map(qnARequest -> QnA.builder()
                        .question(qnARequest.question())
                        .answer(qnARequest.answer())
                        .reviewId(review.getId())
                        .codeId(qnARequest.codeId())
                        .build())
                .toList();
        commandReviewPort.saveAllQnAs(qnAs);
    }
}
