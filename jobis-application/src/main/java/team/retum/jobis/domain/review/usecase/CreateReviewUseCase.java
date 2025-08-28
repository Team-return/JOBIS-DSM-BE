package team.retum.jobis.domain.review.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.review.dto.request.CreateReviewRequest;
import team.retum.jobis.domain.review.exception.ReviewAlreadyExistsException;
import team.retum.jobis.domain.review.model.QnA;
import team.retum.jobis.domain.review.model.Question;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.spi.CommandQuestionPort;
import team.retum.jobis.domain.review.spi.ReviewPort;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.student.model.Student;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CreateReviewUseCase {

    private final SecurityPort securityPort;
    private final QueryCompanyPort queryCompanyPort;
    private final ReviewPort reviewPort;
    private final QueryApplicationPort queryApplicationPort;

    public void execute(CreateReviewRequest request) {
        Student student = securityPort.getCurrentStudent();
        Company company = queryCompanyPort.getById(request.companyId())
            .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        queryApplicationPort.getByCompanyIdAndStudentIdOrThrow(company.getId(), student.getId())
            .checkReviewAuthority();

        if (reviewPort.existsByCompanyIdAndStudentId(request.companyId(), student.getId())) {
            throw ReviewAlreadyExistsException.EXCEPTION;
        }

        Review review = reviewPort.save(
            Review.builder()
                .companyId(request.companyId())
                .studentId(student.getId())
                .codeId(request.jobCode())
                .interviewType(request.interviewType())
                .interviewLocation(request.location())
                .interviewerCount(request.interviewerCount())
                .question(request.question())
                .answer(request.answer())
                .build()
        );

        List<QnA> qnAs = new ArrayList<>(request.qnas().stream()
            .map(qnARequest -> QnA.builder()
                .questionId(qnARequest.questionId())
                .answer(qnARequest.answer())
                .reviewId(review.getId())
                .build()).toList());

        reviewPort.saveAll(qnAs);
    }
}
