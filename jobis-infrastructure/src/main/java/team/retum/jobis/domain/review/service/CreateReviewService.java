package team.retum.jobis.domain.review.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.persistence.ApplicationEntity;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.code.facade.CodeFacade;
import team.retum.jobis.domain.company.persistence.repository.CompanyRepository;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.review.persistence.QnAElement;
import team.retum.jobis.domain.review.persistence.Review;
import team.retum.jobis.domain.review.persistence.repository.ReviewRepository;
import team.retum.jobis.domain.review.exception.ReviewAlreadyExistsException;
import team.retum.jobis.domain.review.presentation.dto.CreateReviewRequest;
import team.retum.jobis.domain.student.persistence.Student;
import team.retum.jobis.domain.student.persistence.repository.StudentRepository;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.Service;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CreateReviewService {

    private final UserFacade userFacade;
    private final StudentRepository studentRepository;
    private final ApplicationRepository applicationRepository;
    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;
    private final CodeFacade codeFacade;

    public void execute(CreateReviewRequest request) {
        if (!companyRepository.existsCompanyById(request.getCompanyId())) {
            throw CompanyNotFoundException.EXCEPTION;
        }
        ApplicationEntity applicationEntity = applicationRepository.queryApplicationById(request.getApplicationId())
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);

        applicationEntity.checkReviewAuthority();

        List<Long> codeIds = request.getQnaElements().stream()
                .map(QnAElement::getCodeId)
                .toList();

        codeFacade.queryCodesByIdIn(codeIds);

        Student student = studentRepository.queryStudentById(userFacade.getCurrentUserId())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        if (reviewRepository.existsByCompanyIdAndStudentName(request.getCompanyId(), student.getName())) {
            throw ReviewAlreadyExistsException.EXCEPTION;
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
