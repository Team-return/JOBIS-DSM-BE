package com.example.jobisapplication.domain.review.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.application.model.Application;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import com.example.jobisapplication.domain.company.exception.CompanyNotFoundException;
import com.example.jobisapplication.domain.company.model.Company;
import com.example.jobisapplication.domain.company.spi.QueryCompanyPort;
import com.example.jobisapplication.domain.review.dto.CreateReviewRequest;
import com.example.jobisapplication.domain.review.model.Review;
import com.example.jobisapplication.domain.review.spi.CommandReviewPort;
import com.example.jobisapplication.domain.review.spi.QueryReviewPort;
import com.example.jobisapplication.domain.student.model.Student;
import com.example.jobisapplication.domain.student.spi.QueryStudentPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.review.exception.ReviewAlreadyExistsException;
import com.example.jobisapplication.domain.student.exception.StudentNotFoundException;

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
